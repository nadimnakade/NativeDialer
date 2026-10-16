# AGENTS.md — 360 Connect Dialer

Production CRM Dialer Android app ("360 Connect") with a .NET backend. Read this before touching the repo.

## Repo Structure

```
NativeDialer/                    (parent repo, branch: master)
├── Frontend/                    (git SUBMODULE, branch: main) — Android app
├── Backend/                     (DialerAPI — secondary .NET project in this repo)
├── cloudconnect.xml             (SIP/cloud config)
├── docs/                        (superpowers plans/specs)
└── WS/PROJECT_STATUS.md
```

**Actual production backend lives OUTSIDE this repo:**
- `D:\1up\Api\PickupAPi` — ASP.NET Web API 2 (`UpdateStatusController`, `SqlHelper`, stored procedures)
- SQL scripts: `D:\1up\Api\PickupAPi\SQL\*.sql` — **files only, NOT auto-deployed. Run manually on SQL Server.**

Git remote (both repos): `https://github.com/nadimnakade/NativeDialer.git`

## Frontend (Android)

- Kotlin + Jetpack Compose, Hilt DI, Room, Retrofit/OkHttp, PJSIP native libs
- Namespace: `com.crm.v2`, but `applicationId = "io.uppro.dialer"`
- minSdk 26 (PJSIP pre-built for API 26+), targetSdk 35
- Launch: `adb shell am start -n io.uppro.dialer/com.crm.v2.presentation.MainActivity`

### Build Commands

Shell is PowerShell — `&&` does not work. Use `cmd /c` with env vars:

```powershell
# Debug
cmd /c "set JAVA_HOME=C:\Program Files\Android\Android Studio\jbr&& set ANDROID_HOME=C:\Users\nadim\AppData\Local\Android\Sdk&& cd /d D:\Git_Repos\Dialer - Copy\Frontend && gradlew.bat assembleDebug"

# Release (R8 minified, ~15MB vs debug ~33MB)
cmd /c "... gradlew.bat assembleRelease"
```

- Debug APK: `Frontend\app\build\outputs\apk\debug\app-debug.apk`
- Release APK: `Frontend\app\build\outputs\apk\release\app-release.apk`
- Install workaround: copy APK to `C:\Users\nadim\AppData\Local\Temp\app-debug.apk` first (source path has spaces)
- `adb` full path: `C:\Users\nadim\AppData\Local\Android\Sdk\platform-tools\adb.exe`
- Python: `C:\Python314\python.exe`

### Testing Constraints

- **Emulator cannot resolve `1up.co.in`** (DNS) — all network testing must be on a physical device
- Release build strips logs — use debug build + `adb logcat` for diagnosis (tags: `EXTENSION_CONFIG`, `CLOUD_CONNECT`, `UPDATE`)

## Architecture Decisions (current)

### SIP extension config flow

1. XML config: `CloudConnectConfigProvider` fetches `cloudconnect.xml` (defaults, incl. extension `802`)
2. DB override: `ExtensionConfigProvider.fetchAndApplyExtension(empCode)` calls `GET UpdateStatusController/GetExtension?EmpCode=...`, overrides `ApiConstants` values
3. Runs at: login (`LoginViewModel`) + app startup for existing sessions (`CrmApplication`)
4. UI: `SipInfoCard` on Dashboard (debug) — keyed on `extensionStatus.isLoaded` to force recomposition (`ApiConstants` are plain vars, not Compose state)

**Employee-specific vs common row:**
- `USP_GET_EMPLOYEE_EXTENSION_API` matches `EMP_CODE = @EmpCode` first, falls back to `EMP_CODE IS NULL` common row
- Deployed via `D:\1up\Api\PickupAPi\SQL\USP_GET_EMPLOYEE_EXTENSION_API.sql` (manual)

### Known root cause: shared SIP extension

Multiple agents on ONE SIP extension kick each other's registration (intermittent failures). **Decided fix: server-side SIP bridge** — one WS/ service holds a single SIP registration server-side. **NOT YET IMPLEMENTED.**

- SIP server: `sip2.cloud-connect.in` (43.205.16.105) — port 7065 (SIP UDP) reachable, port 7443 (WSS) CLOSED
- This blocks the bridge work

### Database

- `TBL_CIP_EMPLOYEE_DID` — per-employee DID/extension: `EMP_CODE`, `DID_NUMBER`, `EXTENSION_NUMBER`, `DID_STATUS`, `BASE_URL`, `TOKEN_ID`, `EXTENSION_PASSWORD`, `SIP_SERVER`, `SIP_PORT`, `SIP_PASSWORD`, `SIP_REALM`, `IS_DELETED`, ... Common row = `EMP_CODE IS NULL`.
- `TBL_CIP_EMPLOYEE_MASTER.IS_NEW_DIALER` — app sets to `1` on login (`UpdateNewDialerStatus` endpoint) to flag new-dialer users
- `User.key` = EmployeeCode (empcode) — no separate employeeCode field

## In-App Update Mechanism

- `UpdateChecker` (Frontend): fetches `https://1up.co.in/NEXUS_API/prodialer_new.xml`, compares `<version>` against `BuildConfig.VERSION_NAME` using `normalizeVersion` + `compareVersions` (server must be **strictly greater**; `-debug` suffix stripped)
- XML format:
  ```xml
  <update><version>2.1.1</version><name>360 Connect</name><url>https://1up.co.in/DialerAPI/prodialer_new.apk</url></update>
  ```
- Download → FileProvider (`cache-path updates/`) → `ACTION_VIEW` install intent
- **Requires `REQUEST_INSTALL_PACKAGES`** manifest permission (added in 2.1.1) — without it installer silently does nothing on Android 8+
- Already-installed old APKs lack the permission → in-app update still dead until users sideload one build with the permission

## API Constants Pattern

`ApiConstants` is a Kotlin `object` with mutable `var`s (`ApiConstants.Sip.*`, `ApiConstants.CloudConnect.*`). Updated via `ApiConstants.updateFromConfig(CloudConnectConfig)`. Compose does NOT track reads of these — UI reading them needs an explicit recomposition trigger.

Base URLs: REST `https://1up.co.in/NEXUS_API/api/` (moved from old `PickupAPi` path).

## Current Version

`versionName 2.1.1`, `versionCode 10` (note: was briefly 11 — downgrade to 10 was intentional, installed devices may need `adb install -r -d`).

## Git Workflow

- **Frontend is a submodule** — commit it FIRST, then update parent pointer
- Build junk (`.gradle/`, `app/build/`) is tracked from history; stage source paths explicitly (`app/src`, `build.gradle.kts`, `.gitignore`), not `git add -A`
- Do not commit unless explicitly asked

## Pending / Open Work

1. Deploy updated `USP_GET_EMPLOYEE_EXTENSION_API` to SQL Server + insert common row (`EMP_CODE IS NULL`) — if extension shows XML default `802`, SP not deployed or row missing
2. Verify `prodialer_new.xml` live at NEXUS_API path (was 404 during check)
3. Server-side SIP bridge (blocked: port 7443 closed)
4. `NetworkGate` composable in `MainActivity` (Task 5, network connectivity work — see `docs/superpowers/plans/2026-08-21-network-connectivity.md`)

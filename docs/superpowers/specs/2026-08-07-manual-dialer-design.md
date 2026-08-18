# Manual Dialer Tab — Design

## Goal
Add a 4th "Manual" tab to the Dialer screen so the user can type any phone number
(no saved contact needed) and place a call through the same PJSIP + Update-Status
flow used for contacts and leads.

## Scope
- New "Manual" dialpad tab in `DialerScreen.kt`.
- Number entry wired to the existing `phoneNumber` field in `DialerUiState`.
- Call goes through the existing `manualCall()` path; after the call ends the
  existing post-call → Update Status flow fires.
- Add a "Client Name" input to `UpdateStatusScreen` (only for manual, i.e. empty leadKey).
- Save path for manual reuses the existing `updateDialer` path (TODO noted for
  a future `Dialer/lead-info-new` POST).

## Out of scope
- DND pre-check.
- Renaming "Contact" → "Manual".
- Full gap analysis of the Ionic app.
- Call recording.
- Changes to repos / API contracts.

## UI
- Large centered number display.
- 4×3 dialpad (1-9, *, 0, #), circular keys with letters under digits.
- Backspace key; long-press to clear.
- Green call FAB, disabled when empty.

## Behavior
1. Key tap appends digit to `phoneNumber`.
2. Call button: validate length >= 6; set `currentLead = DialerRecord(key:"", name:"", contact:number)`; call `viewModel.manualCall(number)`.
3. Post-call → `showUpdateStatus` fires with the manual lead.

## Files
- `presentation/ui/dialer/DialerScreen.kt` — add tab + composable.
- `presentation/ui/dialer/DialerViewModel.kt` — `callManualNumber(number)` helper.
- `presentation/ui/dialer/UpdateStatusScreen.kt` — conditional Client Name field.
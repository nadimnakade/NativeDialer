# Dashboard Redesign Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Redesign the dashboard screen (`DashboardScreen.kt`) to match the clean modern aesthetic and layout specified in the design spec.

**Architecture:** Update Compose UI components in `DashboardScreen.kt` and subcomponents to implement the header greeting, dual 2-column action cards (Dialer and Add Lead), dark Follow-ups banner card, Performance Overview with Disbursed and Rank trend cards with sparkline visuals, compact points/FTD/leads & duration/connectivity stats bento grid, and recent activity feed.

**Tech Stack:** Jetpack Compose, Material 3, Kotlin.

## Global Constraints

- Preserve all existing ViewModel bindings and navigation actions (`onNavigateToDialer`, `onNavigateToLeads`, `onNavigateToFollowUps`, `onNavigateToReports`).
- Adhere to project styling and color conventions (`Background`, `Primary`, `Tertiary`, `GlassSurface`).

---

## Task 1: Redesign Dashboard UI in DashboardScreen.kt

**Files:**
- Modify: `D:\Git_Repos\Dialer - Copy\Frontend\app\src\main\java\com\crm\v2\presentation\ui\dashboard\DashboardScreen.kt`

**Interfaces:**
- Consumes: `DashboardUiState`, `DashboardViewModel`
- Produces: Polished dashboard UI matching the reference design layout.

- [ ] **Step 1: Refactor Greeting Section and Action Cards**
  - Update `DashboardScreen` layout to feature the greeting card ("Hello, [Name]") with clean typography and spacing, followed by the 2-column bento action cards (Dialer with soft red tint & arrow button, Add Lead with soft blue tint & arrow button), and the dark Follow-ups banner card.

- [ ] **Step 2: Refactor Performance Overview & Stats Bento Grid**
  - Implement Performance Overview header with date dropdown and calendar icon.
  - Implement Disbursed and Rank trend cards with amount/rank values, change indicators, and sparklines.
  - Implement compact 3-item stat row (Points, FTD, Leads) and 2-item stat row (Duration, Connectivity).
  - Implement Recent Activity section with "View all >" link and recent call activity card.

- [ ] **Step 3: Compile and Test Build**
  - Run build command to verify zero compilation errors.

```bash
.\gradlew.bat :app:compileDebugKotlin --console=plain
```

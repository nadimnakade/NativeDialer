# Dashboard Redesign Specification

## Overview
Redesign the CRM Dialer mobile app dashboard to match the modern, polished UI style shown in the reference design (clean light background, greeting banner, 2-column bento action cards for Dialer and Add Lead, dark Follow-ups banner, Performance Overview with Disbursed and Rank cards with sparklines, stat grid, and recent activity list).

## Architecture & Components

1. **Header & Greeting Banner**
   - Personalized employee greeting with subtitle and encouraging badge ("You're doing Great!").

2. **Primary Quick Action Cards (2-Column Bento)**
   - **Dialer Card**: Soft pink/red container (`#FFF1F2`), phone icon, "Dialer", "Start calling", and red circular action button.
   - **Add Lead Card**: Soft blue/neutral container, user-add icon, "Add Lead", "New contact", and red circular action button.

3. **Follow-ups Banner**
   - Dark navy gradient/solid card (`#1E1B4B`), clock icon, "Follow-ups", "View pending callbacks", right chevron.

4. **Performance Overview Section**
   - Dropdown selector ("Today") with calendar icon.
   - **Disbursed Card**: Large formatted currency amount, green growth indicator, subtle red sparkline graph.
   - **Rank Card**: Rank number, status label, yellow trend sparkline graph.

5. **Compact Stats Bento Grid**
   - **Row 1**: Points, FTD, Leads cards with red icon pills.
   - **Row 2**: Duration, Connectivity percentage cards.

6. **Recent Activity Section**
   - Header with "View all >" link.
   - Activity card showing lead initials, name, date, masked phone number, timestamp, and green "Connected" status pill.

## Implementation Plan
- Refactor `DashboardScreen.kt` to update layout, spacing, typography, and card components to match the specifications.
- Ensure all actions (`onNavigateToDialer`, `onNavigateToLeads`, `onNavigateToFollowUps`, `onNavigateToReports`) remain fully wired.
- Verify compilation and UI test via gradle build.

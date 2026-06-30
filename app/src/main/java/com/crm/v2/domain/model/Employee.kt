package com.crm.v2.domain.model

data class Employee(
    val key: String,
    val employeeNo: String? = null,
    val employeeCode: String? = null,
    val employeeName: String,
    val userName: String? = null,
    val designationDesc: String? = null,
    val roleDesc: String? = null,
    val location: String? = null,
    val points: Double? = null,
    val incentives: Double? = null,
    val rank: Int? = null,
    val photo: String? = null,
)

data class DashboardData(
    val rank: Int = 0,
    val points: Double = 0.0,
    val totalDisbursedAmt: Double = 0.0,
    val clientConnectedFtd: Int = 0,
    val duration: String = "00:00:00",
    val dailyLeads: Int = 0,
    val monthlyLeads: Int = 0,
    val loginCount: Int = 0,
    val loginAmount: Double = 0.0,
    val downTime: String = "00:00:00",
    val connectivityPercent: Double = 0.0,
    val disbursalBreakdown: List<DisbursalBreakdown> = emptyList(),
)

data class DisbursalBreakdown(
    val loanType: String,
    val amount: Double,
    val count: Int,
)

data class PerformanceData(
    val key: String,
    val value: Double,
)

data class RankingData(
    val currentMonth: List<RankEntry> = emptyList(),
    val previousMonth: List<RankEntry> = emptyList(),
)

data class RankEntry(
    val rank: Int,
    val employeeName: String,
    val points: Double,
    val calls: Int,
    val duration: String? = null,
)

data class AttendanceData(
    val totalDays: Int = 0,
    val salary: Double = 0.0,
    val salaryEarned: Double = 0.0,
    val absentDays: Int = 0,
    val halfDay: Int = 0,
    val late: Int = 0,
    val workingDays: Int = 0,
)

data class DisbursalDetail(
    val loanId: String,
    val clientName: String,
    val loanAmount: Double,
    val disbursedAmount: Double,
    val disbursalDate: String? = null,
    val bankName: String? = null,
    val status: String? = null,
)

data class IncentiveData(
    val totalIncentive: Double = 0.0,
    val incentivePerDay: List<DailyIncentive> = emptyList(),
    val totalCalls: Int = 0,
)

data class DailyIncentive(
    val date: String,
    val amount: Double,
    val calls: Int,
)

data class LiveCount(
    val dnd: Int = 0,
    val lead: Int = 0,
    val followUp: Int = 0,
    val notInterested: Int = 0,
    val notDoable: Int = 0,
    val wrongNo: Int = 0,
    val blank: Int = 0,
    val interested: Int = 0,
    val notContactable: Int = 0,
    val ringing: Int = 0,
    val totalCount: Int = 0,
    val totalClientConnected: Int = 0,
    val duration: String = "00:00:00",
    val totalLoginAmt: Double = 0.0,
    val totalLogin: Int = 0,
    val totalDisbursedAmt: Double = 0.0,
    val totalDisbursedCount: Int = 0,
    val dailyLogin: Int = 0,
    val dailyLoginAmt: Double = 0.0,
)

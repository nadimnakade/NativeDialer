using System.Data;
using DialerAPI.Models;
using Microsoft.Data.SqlClient;

namespace DialerAPI.Data;

public class DialerHelper
{
    private readonly string _connectionString;

    public DialerHelper(string connectionString) => _connectionString = connectionString;

    private static DataTable ToDataTable<T>(List<T> items)
    {
        var table = new DataTable(typeof(T).Name);
        var props = typeof(T).GetProperties(System.Reflection.BindingFlags.Public | System.Reflection.BindingFlags.Instance);
        foreach (var prop in props) table.Columns.Add(prop.Name);
        foreach (var item in items)
        {
            var values = new object[props.Length];
            for (int i = 0; i < props.Length; i++)
                values[i] = props[i].GetValue(item, null) ?? DBNull.Value;
            table.Rows.Add(values);
        }
        return table;
    }

    public int FnSaveCallLogs(List<CallLogListDto> lst)
    {
        using var conn = new SqlConnection(_connectionString);
        conn.Open();
        using var bulk = new SqlBulkCopy(conn);
        bulk.DestinationTableName = "TBL_CALL_LOGS";
        bulk.ColumnMappings.Add("name", "name");
        bulk.ColumnMappings.Add("date", "date");
        bulk.ColumnMappings.Add("duration", "duration");
        bulk.ColumnMappings.Add("type", "type");
        bulk.ColumnMappings.Add("userkey", "userkey");
        bulk.ColumnMappings.Add("number", "number");
        bulk.WriteToServer(ToDataTable(lst));
        return 1;
    }

    public int FnSaveCallLogsAll(List<CallLogListDto> lst)
    {
        using var conn = new SqlConnection(_connectionString);
        conn.Open();
        using var bulk = new SqlBulkCopy(conn);
        bulk.DestinationTableName = "TBL_CALL_LOGS_ATTENDANCE";
        bulk.ColumnMappings.Add("name", "name");
        bulk.ColumnMappings.Add("date", "date");
        bulk.ColumnMappings.Add("duration", "duration");
        bulk.ColumnMappings.Add("type", "type");
        bulk.ColumnMappings.Add("userkey", "userkey");
        bulk.ColumnMappings.Add("number", "number");
        bulk.WriteToServer(ToDataTable(lst));
        return 1;
    }

    public int FnIndvCallLog(List<CallLogListDto> lst)
    {
        using var conn = new SqlConnection(_connectionString);
        conn.Open();
        using var bulk = new SqlBulkCopy(conn);
        bulk.DestinationTableName = "TBL_CALL_LOGS_ATTENDANCE";
        bulk.ColumnMappings.Add("name", "name");
        bulk.ColumnMappings.Add("date", "date");
        bulk.ColumnMappings.Add("duration", "duration");
        bulk.ColumnMappings.Add("type", "type");
        bulk.ColumnMappings.Add("userkey", "userkey");
        bulk.ColumnMappings.Add("number", "number");
        bulk.ColumnMappings.Add("source", "source");
        var dt = ToDataTable(lst);
        dt.Columns.Add("source", typeof(string));
        foreach (DataRow row in dt.Rows) row["source"] = "Dialer";
        bulk.WriteToServer(dt);
        return 1;
    }
}

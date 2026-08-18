using Dapper;
using Microsoft.Data.SqlClient;

namespace WsClient.Data;

public class DbConnectionFactory
{
    private readonly string _connectionString;

    public DbConnectionFactory(string connectionString)
    {
        _connectionString = connectionString;
    }

    public SqlConnection Create() => new(_connectionString);

    public async Task<IEnumerable<T>> QueryAsync<T>(string storedProc, object? parameters = null)
    {
        using var conn = Create();
        return await conn.QueryAsync<T>(storedProc, parameters, commandType: System.Data.CommandType.StoredProcedure);
    }

    public async Task<T?> QuerySingleAsync<T>(string storedProc, object? parameters = null)
    {
        using var conn = Create();
        return await conn.QuerySingleOrDefaultAsync<T>(storedProc, parameters, commandType: System.Data.CommandType.StoredProcedure);
    }

    public async Task<int> ExecuteAsync(string storedProc, object? parameters = null)
    {
        using var conn = Create();
        return await conn.ExecuteAsync(storedProc, parameters, commandType: System.Data.CommandType.StoredProcedure);
    }

    public async Task<IEnumerable<dynamic>> QueryAsync(string storedProc, object? parameters = null)
    {
        using var conn = Create();
        return await conn.QueryAsync(storedProc, parameters, commandType: System.Data.CommandType.StoredProcedure);
    }
}

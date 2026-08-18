using DialerAPI.Data;
using DialerAPI.Services;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();
builder.Services.AddCors(o => o.AddDefaultPolicy(p => p.AllowAnyOrigin().AllowAnyMethod().AllowAnyHeader()));

var connString = builder.Configuration.GetConnectionString("DefaultConnection") ?? "";
builder.Services.AddSingleton(new DbConnectionFactory(connString));
builder.Services.AddSingleton(new DialerHelper(connString));

builder.Services.AddSingleton<CallSessionManager>();
builder.Services.AddSingleton<SipWebSocketService>();
builder.Services.AddHostedService(sp => sp.GetRequiredService<SipWebSocketService>());

var app = builder.Build();

app.UseCors();
app.MapControllers();

app.MapGet("/", () => Results.Ok(new { status = "running", timestamp = DateTime.UtcNow }));

app.Run();

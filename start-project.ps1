# start-project.ps1

# Перейти в папку backend
Write-Host "Запускаем backend..."
cd C:\Users\Vorontsov\realworld-portfolio\backend

# Запустить backend в отдельном окне PowerShell, чтобы он не блокировал
Start-Process powershell -ArgumentList "-NoExit", "-Command", "docker-compose up"

# Немного подождать, чтобы backend успел подняться
Start-Sleep -Seconds 10

# Перейти в папку frontend
Write-Host "Запускаем frontend..."
cd C:\Users\Vorontsov\realworld-portfolio\frontend

# Запустить frontend (этот процесс останется в текущем окне)
npm start
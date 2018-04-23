call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runpage
echo There were errors
goto end

:runpage
start http://localhost:8888/crud/v1/task/getTasks


:end
echo.
Work is finished
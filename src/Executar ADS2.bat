@echo off
title Sistema de VOD do Grupo 4 - ADS2
cd /d "%~dp0"

echo.
echo -------------------------
echo   Iniciando o sistema...
echo -------------------------
echo.

java -jar ADS2.jar

echo.
echo -----------------------------------------
echo   Programa encerrado.
echo   Pressione qualquer tecla para fechar.
echo -----------------------------------------
pause >nul
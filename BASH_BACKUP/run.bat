@echo off
set MAIN_CLASS=Main
set JAVA_AGENT_PATH=E:\projects\java\hotswap-agent-2.0.1.jar


mvn compile
mvn exec:java "-Dexec.mainClass=Main"

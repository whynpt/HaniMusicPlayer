$ErrorActionPreference = "Stop"

$RootDir = Split-Path -Parent $PSScriptRoot
Set-Location $RootDir

$androidStudioJbr = "C:\Program Files\Android\Android Studio\jbr"
if (-not $env:JAVA_HOME -and (Test-Path $androidStudioJbr)) {
    $env:JAVA_HOME = $androidStudioJbr
}

.\gradlew.bat --no-daemon --console=plain :app:testDebugUnitTest :app:assembleDebug

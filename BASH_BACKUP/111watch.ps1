while ($true) {
  Get-ChildItem src -Recurse *.java |
    Wait-FileSystemEvent -Event Changed
  mvn -q compile exec:java -Dexec.mainClass=Main
}

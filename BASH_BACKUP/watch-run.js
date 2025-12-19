const { spawn } = require("child_process");
const chokidar = require("chokidar");
const fs = require("fs");
const path = require("path");
const kill = require("tree-kill");


const PID_FILE = "java.pid";
//const SRC_DIR = "E:\\projects\\java\\Course06\\lesson31_home_work_02\\src";
const SRC_DIR = "src";
const MAIN_CLASS = "Main";
const MAVEN_CMD = "C:\\Maven\\bin\\mvn.cmd";

let javaProcess = null;
let restartTimeout = null;

function killPrevious() {
  if (fs.existsSync(PID_FILE)) {
    const pid = parseInt(fs.readFileSync(PID_FILE, "utf8"), 10);
    kill(pid, "SIGKILL", (err) => {
      if (err) console.log(`No process to kill with PID ${pid}`);
      else console.log(`Killed previous Java process tree with PID ${pid}`);
    });
    fs.unlinkSync(PID_FILE);
  }
}

function startJava() {
  console.log("Compiling and starting Java app...");

  // IF WE RUN IN NESTED TERMINAL - WILL NOT WORK
  // Use cmd /c to run mvn.cmd on Windows
  /*
  javaProcess = spawn("cmd", ["/c", MAVEN_CMD, "exec:java", "-Dexec.mainClass=" + MAIN_CLASS], {
    stdio: "inherit",
    cwd: path.resolve(__dirname),
    shell: true
  });
  */
    javaProcess = spawn(MAVEN_CMD, ["compile exec:java", "-Dexec.mainClass=Main"], {
        stdio: "inherit",
        cwd: path.resolve(__dirname),
        shell: true
    });


  fs.writeFileSync(PID_FILE, javaProcess.pid.toString(), "utf8");
  console.log(`Java process started with PID ${javaProcess.pid}`);

  javaProcess.on("exit", (code) => {
    fs.existsSync(PID_FILE) && fs.unlinkSync(PID_FILE);
    console.log(`Java process exited with code ${code}`);
  });
}

// Watch for Java source changes
const watcher = chokidar.watch(SRC_DIR, {
  ignoreInitial: true,
  usePolling: true,
  interval: 200,
  binaryInterval: 300
});


watcher.on("all", (event, filePath) => {
  if (!filePath.endsWith(".java")) return;
  console.log(`File ${filePath} changed (${event})`);


  if (restartTimeout) clearTimeout(restartTimeout);
  restartTimeout = setTimeout(() => {
    console.log("Restarting Java process...");
    killPrevious();
    startJava();
  }, 300);
});

// Start first time
startJava();

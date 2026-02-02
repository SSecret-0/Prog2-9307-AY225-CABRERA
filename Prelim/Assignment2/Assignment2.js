// Hardcoded credentials
const correctUsername = "admin";
const correctPassword = "1234";

// Attendance storage
const attendanceRecords = [];

// Preload beep sound
const beep = new Audio("beep.wav");
beep.preload = "auto";

document.getElementById("loginForm").addEventListener("submit", function (event) {
  event.preventDefault();

  const username = document.getElementById("username").value;
  const password = document.getElementById("password").value;
  const message = document.getElementById("message");
  const timestampDisplay = document.getElementById("timestamp");

  if (username === correctUsername && password === correctPassword) {
    // Capture time
    const now = new Date();

    const formattedTime =
      (now.getMonth() + 1).toString().padStart(2, "0") + "/" +
      now.getDate().toString().padStart(2, "0") + "/" +
      now.getFullYear() + " " +
      now.getHours().toString().padStart(2, "0") + ":" +
      now.getMinutes().toString().padStart(2, "0") + ":" +
      now.getSeconds().toString().padStart(2, "0");

    // Display messages
    message.textContent = "Login successful. Welcome, " + username + "!";
    message.style.color = "green";

    timestampDisplay.textContent = "Login Time: " + formattedTime;

    // Save attendance
    attendanceRecords.push({
      username: username,
      timestamp: formattedTime
    });

    // Generate attendance summary text
    let attendanceText = "Attendance Summary\n\n";

    attendanceRecords.forEach((record, index) => {
      attendanceText +=
        "Record " + (index + 1) + "\n" +
        "Username: " + record.username + "\n" +
        "Timestamp: " + record.timestamp + "\n\n";
    });

    // Create and download file
    const blob = new Blob([attendanceText], { type: "text/plain" });
    const link = document.createElement("a");
    link.href = URL.createObjectURL(blob);
    link.download = "attendance_summary.txt";
    link.click();

  } else {
    message.textContent = "Incorrect username or password.";
    message.style.color = "red";
    timestampDisplay.textContent = "";

    beep.currentTime = 0;
    beep.play();
  }
});

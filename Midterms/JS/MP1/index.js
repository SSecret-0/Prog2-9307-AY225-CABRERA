const fs = require('fs');
const readline = require('readline');
const DataRecord = require('./dataRecord');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function askFilePath() {
    rl.question("Enter dataset file path: ", function(path) {

        // ===== VALIDATION =====
        if (!fs.existsSync(path)) {
            console.log("Error: File does not exist.\n");
            return askFilePath();
        }

        if (!path.toLowerCase().endsWith(".csv")) {
            console.log("Error: File is not in CSV format.\n");
            return askFilePath();
        }

        try {
            const data = fs.readFileSync(path, 'utf8');
            processData(data);
        } catch (error) {
            console.log("Error reading file:", error.message);
            askFilePath();
        }
    });
}

function processData(fileContent) {

    const lines = fileContent.split('\n');

    let missingCount = 0;
    let negativeSalesCount = 0;
    let invalidDateCount = 0;
    let duplicateCount = 0;
    let totalRecords = 0;

    const uniqueRecords = new Set();

    // Skip header
    for (let i = 1; i < lines.length; i++) {

        if (!lines[i].trim()) continue;

        const columns = lines[i].split(',');

        if (columns.length < 4) {
            missingCount++;
            continue;
        }

        const record = new DataRecord(
            columns[0].trim(),
            columns[1].trim(),
            columns[2].trim(),
            columns[3].trim()
        );

        if (record.hasMissingValues())
            missingCount++;

        if (record.hasNegativeSales())
            negativeSalesCount++;

        if (!record.isValidDate())
            invalidDateCount++;

        const key = record.generateUniqueKey();
        if (uniqueRecords.has(key))
            duplicateCount++;
        else
            uniqueRecords.add(key);

        totalRecords++;
    }

    console.log("\n===== DATA QUALITY REPORT =====\n");
    console.log("Total Records Processed :", totalRecords);
    console.log("Missing Values Detected :", missingCount);
    console.log("Negative Sales Detected :", negativeSalesCount);
    console.log("Invalid Dates Detected  :", invalidDateCount);
    console.log("Duplicate Records Found :", duplicateCount);
    console.log("\n================================");

    rl.close();
}

askFilePath();
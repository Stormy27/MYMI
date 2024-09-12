const express = require('express');
const axios = require('axios');

const app = express();
const port = 3210;

app.use(express.json());

const meterIds = ["smartmeter-1", "smartmeter-2", "smartmeter-3"];

const smartMeters = {
    transactionId: "r12xk1",
    smartMeters: [
        {
            id: meterIds[0],
            ipAddress: "192.168.0.1"
        },
        {
            id: meterIds[1],
            ipAddress: "192.168.0.2"
        },
        {
            id: meterIds[2],
            ipAddress: "192.168.0.3"
        }
    ]
};

app.get('/read', (req, res) => {
    console.log('Sending generated data');
    res.json(generateSmartMeterData());
});

const postDataAfterStart = async () => {
    try {
        const response = await axios.post('http://localhost:8080/api/smartmeters/register', smartMeters);
        console.log('POST request successful:', response.data);
    } catch (error) {
        console.error('Error performing POST request:', error);
    }
};

app.listen(port, () => {
    console.log(`Server running at http://localhost:${port}`);
    postDataAfterStart();
});

function generateSmartMeterData() {
    const getRandomValue = (min, max) => (Math.random() * (max - min) + min).toFixed(2);
    const getTimestamp = (hourOffset) => {
        const date = new Date();
        date.setHours(date.getHours() - hourOffset);
        return date.toISOString();
    };

    const timestamps = [
        new Date(new Date().setMinutes(0, 0, 0)).toISOString(),
        new Date(new Date().setMinutes(15, 0, 0)).toISOString(),
        new Date(new Date().setMinutes(30, 0, 0)).toISOString(),
        new Date(new Date().setMinutes(45, 0, 0)).toISOString()
    ];

    const voltage = {};
    const current = {};

    timestamps.forEach((timestamp) => {
        voltage[timestamp] = parseFloat(getRandomValue(240, 250));
        current[timestamp] = parseFloat(getRandomValue(40, 60));
    });

    return {
        id: meterIds[Math.floor(Math.random() * 3)],
        voltage: voltage,
        current: current
    };
}
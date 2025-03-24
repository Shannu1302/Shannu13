const apiKey = 'your_openweathermap_api_key';  // Replace with your API key

document.getElementById('getWeatherBtn').addEventListener('click', getWeather);

async function getWeather() {
    const city = document.getElementById('city').value;
    
    if (city === "") {
        alert("Please enter a city name.");
        return;
    }

    const weatherResult = document.getElementById('weatherResult');
    weatherResult.innerHTML = "Loading...";  // Show loading text

    const url = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric`;

    try {
        const response = await fetch(url);
        const data = await response.json();

        if (data.cod === "404") {
            weatherResult.innerHTML = `<p class="error">City not found.</p>`;
        } else {
            const weatherDetails = `
                <p><strong>City:</strong> ${data.name}</p>
                <p><strong>Temperature:</strong> ${data.main.temp} °C</p>
                <p><strong>Weather:</strong> ${data.weather[0].description}</p>
                <p><strong>Humidity:</strong> ${data.main.humidity}%</p>
                <p><strong>Wind Speed:</strong> ${data.wind.speed} m/s</p>
            `;
            weatherResult.innerHTML = weatherDetails;
        }
    } catch (error) {
        weatherResult.innerHTML = `<p class="error">Error fetching data. Please try again later.</p>`;
    }
}

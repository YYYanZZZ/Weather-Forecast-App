# 🌦️ Weather Forecast App | 天气预报应用

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com/)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)](https://android-arsenal.com/api?level=21)

[English](#english) | [中文](#chinese)

![App Banner](app/src/main/res/mipmap-xxhdpi/sunny.png)

<a id="english"></a>
## 📱 Weather Forecast App

A beautiful, feature-rich weather forecast application that provides accurate and detailed weather information for multiple cities.

### ✨ Features

- 🌡️ **Real-time Weather Data** - Current temperature, weather conditions, wind information and more
- 📅 **7-Day Forecast** - Plan your week with accurate daily weather predictions
- 🏙️ **Multi-City Management** - Add, search, and manage multiple city weather profiles
- 📍 **Location Services** - Get weather updates for your current location
- 🧥 **Life Indices** - Clothing, driving, cold, sport and UV indices to plan your day
- 🔄 **Real-time Updates** - Latest weather data from professional weather services
- 🎨 **Beautiful UI** - Elegant interface with customizable background themes
- 📱 **User-Friendly Design** - Intuitive swipe gestures and easy navigation

### 📱 Screenshots

[Place screenshots here]

### 🛠️ Technologies Used

- **Java** - Primary programming language
- **High Precision APIs** - Utilizing Gaode Location API and QWeather API
- **SQLite Database** - For local storage of city and weather information
- **ViewPager** - For smooth transitions between city weather pages
- **Fragment-based Architecture** - For modular and maintainable code

### 🚀 Getting Started

#### Prerequisites

- Android Studio 4.0+
- Android SDK 21+ (Android 5.0 or above)
- Gaode Maps API key (for location services)
- QWeather API key (for weather data)

#### Installation

1. Clone this repository
```bash
git clone https://github.com/YYYanZZZ/Weather-Forecast-App.git
```

2. Open the project in Android Studio

3. Replace the API keys in the AndroidManifest.xml file:
```xml
<meta-data 
    android:name="com.amap.api.v2.apikey" 
    android:value="YOUR_GAODE_API_KEY">
</meta-data>
```

4. Update the API key in CityWeatherFragment.java:
```java
String keyurl = "&key=YOUR_QWEATHER_API_KEY";
```

5. Build and run the application

### 🔮 Future Enhancements

- 🌚 Dark mode support
- 🔔 Weather alerts and notifications
- 📊 Historical weather data and graphs
- 🖼️ Additional customization options
- 🌐 Support for more languages

### 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

### 🙏 Acknowledgements

- [QWeather API](https://dev.qweather.com/) for providing weather data
- [Gaode Map](https://lbs.amap.com/) for location services
- All contributors who have helped this project grow

---

<a id="chinese"></a>
## 📱 天气预报应用

一款美观、功能丰富的天气预报应用，为多个城市提供准确详细的天气信息。

### ✨ 功能特点

- 🌡️ **实时天气数据** - 当前温度、天气状况、风力信息等
- 📅 **七天天气预报** - 通过准确的每日天气预测规划您的一周
- 🏙️ **多城市管理** - 添加、搜索和管理多个城市的天气信息
- 📍 **位置服务** - 获取您当前位置的天气更新
- 🧥 **生活指数** - 穿衣、驾车、感冒、运动和紫外线指数帮助您规划一天
- 🔄 **实时更新** - 来自专业天气服务的最新天气数据
- 🎨 **精美界面** - 优雅的界面设计，可自定义背景主题
- 📱 **用户友好设计** - 直观的滑动手势和便捷的导航

### 📱 应用截图

[放置应用截图]

### 🛠️ 技术栈

- **Java** - 主要编程语言
- **高精度API** - 使用高德定位API和和风天气API
- **SQLite数据库** - 用于本地存储城市和天气信息
- **ViewPager** - 实现城市天气页面之间的平滑过渡
- **基于Fragment的架构** - 提供模块化和可维护的代码结构

### 🚀 开始使用

#### 前提条件

- Android Studio 4.0+
- Android SDK 21+ (Android 5.0或以上)
- 高德地图API密钥（用于位置服务）
- 和风天气API密钥（用于天气数据）

#### 安装步骤

1. 克隆此仓库
```bash
git clone https://github.com/YYYanZZZ/Weather-Forecast-App.git
```

2. 在Android Studio中打开项目

3. 在AndroidManifest.xml文件中替换API密钥：
```xml
<meta-data 
    android:name="com.amap.api.v2.apikey" 
    android:value="您的高德API密钥">
</meta-data>
```

4. 在CityWeatherFragment.java中更新API密钥：
```java
String keyurl = "&key=您的和风天气API密钥";
```

5. 构建并运行应用程序

### 🔮 未来增强功能

- 🌚 深色模式支持
- 🔔 天气预警和通知
- 📊 历史天气数据和图表
- 🖼️ 更多自定义选项
- 🌐 支持更多语言

### 🤝 贡献

欢迎贡献！请随时提交拉取请求。

1. Fork此仓库
2. 创建您的功能分支（`git checkout -b feature/令人惊叹的功能`）
3. 提交您的更改（`git commit -m '添加一些令人惊叹的功能'`）
4. 推送到分支（`git push origin feature/令人惊叹的功能`）
5. 打开拉取请求

### 📄 许可证

本项目采用MIT许可证 - 详情请参阅LICENSE文件。

### 🙏 致谢

- [和风天气API](https://dev.qweather.com/)提供天气数据
- [高德地图](https://lbs.amap.com/)提供位置服务
- 所有帮助本项目成长的贡献者

---

⭐ 如果您觉得这个项目有帮助，请考虑在GitHub上给它一个星标！ ⭐ 
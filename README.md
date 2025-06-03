# Sealog-Logging

![License](https://img.shields.io/badge/license-MIT-green)  
![Java](https://img.shields.io/badge/language-Java-blue)  
![Spring Boot](https://img.shields.io/badge/framework-Spring%20Boot-brightgreen)
![Issues](https://img.shields.io/github/issues/max3no/sealog-logging)

---

## 🔗 Overview

**Sealog-Logging** is a lightweight, tamper-proof logging framework built for Java Spring Boot applications. It creates an immutable chain of logs where each log entry is cryptographically linked to the previous one, ensuring data integrity and traceability. Perfect for audit trails, security-critical applications, and reliable monitoring.

This open-source project enables developers to integrate seamless request and method-level logging with blockchain-inspired hash chaining, making logs verifiable and resistant to tampering.

---

## ✨ Features

- 🔒 **Tamper-proof log chaining** with SHA-256 based hashing
- 📝 **Separate log files** support (e.g., request logs, aspect logs)
- 🌐 Capture client IP and hostname automatically
- 📦 Easy integration with Spring Boot via auto-configuration and AOP aspects
- ⚡ Lightweight and minimal dependencies
- 🔧 Extensible and customizable for advanced use cases

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Spring Boot 3+
- Maven or Gradle build tool

### Installation

**Sealog-Logging is not yet published to Maven Central.**

For now, to use this library in your project:

1. Clone this repository:
   ```bash
   git clone https://github.com/max3no/sealog-logging.git
   cd sealog-logging
2. Install it to your local Maven repository:

```bash
mvn clean install
```

3. Add the dependency in your pom.xml:

```xml
<dependency>
    <groupId>com.max3no</groupId>
    <artifactId>sealog-logging</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
⚠️ Once the library is published to Maven Central, you’ll be able to skip step 2.


## ⚙️ Usage Example
Enable logging by annotating your methods with @Sealog:
```java
import com.max3no.sealog.annotation.Sealog;

@Service
public class UserService {

    @Sealog("User creation invoked")
    public void createUser(String username) {
        // your logic here
    }
}
```
<p>The framework will automatically log HTTP requests as well as annotated method calls, linking all entries with hash chaining to ensure integrity.</p>
<hr/>
<h2>🔍 How It Works</h2>
<ul>
  <li>Each log entry includes a SHA-256 hash of the previous entry, forming a linked chain beginning with a <code>GENESIS</code> hash.</li>
  <li>Logs are serialized as JSON and stored in dedicated files for each logging scope (e.g., filter, aspect).</li>
  <li>Upon application restart, the last hash is restored, preserving the log chain without interruption.</li>
</ul>

<hr/>

<h2>🤝 Contributing</h2>

<p>We welcome contributions! To get started:</p>

<ol>
  <li>Fork the repository</li>
  <li>Create a feature branch: <code>git checkout -b feature/your-feature</code></li>
  <li>Commit your changes: <code>git commit -m "Add feature"</code></li>
  <li>Push the branch: <code>git push origin feature/your-feature</code></li>
  <li>Open a pull request</li>
</ol>

<hr/>

<h2>📄 License</h2>

<p>This project is licensed under the MIT License. See the <a href="LICENSE">LICENSE</a> file for details.</p>

<hr/>

<h2>💬 Contact</h2>

<p>Created and maintained by <strong>Vai</strong>. Open issues or questions? Feel free to reach out via GitHub Issues.</p>

<hr/>
## 🛠 Disclaimer

**This is a work in progress** — feedback, ideas, and contributions are welcome!  
If you find bugs, have feature suggestions, or want to improve the project, feel free to open an issue or pull request.

👉 Star the repo if you find it useful!

<h3>Thank you for using Sealog-Logging!<br/>

🔐 Secure your logs. Trust your data.</h3>









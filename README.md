# 🧱 Mini ORM

A lightweight **Object Relational Mapping (ORM)** framework inspired by popular tools like **Hibernate**.  
This framework is built using **Spring Core** and **Java Reflection**, providing essential ORM functionalities for learning and lightweight use cases.

---

## ✨ Features Supported

### 🔹 1. Automatic Schema Creation  
Automatically generates SQL `CREATE TABLE` queries on application startup by scanning all classes annotated with **`@Entity`**.  

> 💡 Eliminates the need to manually write DDL scripts for entities.

---

### 🔹 2. Dynamic SQL Type Conversion  
Converts Java data types to corresponding **MySQL** column types dynamically during schema generation.  

> 🛠️ Ensures flexibility and minimizes manual mapping effort.

---

### 🔹 3. Supported Data Type Conversions
| Java Type | SQL Type | Notes |
|------------|-----------|-------|
| `int`, `Integer` | `INT` | — |
| `double`, `Double` | `DOUBLE` | — |
| `long`, `Long` | `LONG` | — |
| `String` | `VARCHAR(size)` | Size customizable via `@Column(size = n)` <br> Defaults to `VARCHAR(30)` if not specified |

> ⚠️ Throws `InvalidSqlTypeException` if the field type is unsupported.

---

### 🔹 4. Primary Key Handling (`@Id` Annotation)
- Declaring a single field with `@Id` marks it as the **Primary Key** for that entity.  
- If **no field** or **multiple fields** are annotated with `@Id`, the framework throws a descriptive error.  

> 🧩 Ensures every entity class follows proper primary key conventions.

---

## 🧠 Example Usage
```java
@Entity
public class Book {

    @Id
    private int bookId;

    @Column(size = 100)
    private String bookName;

    @Column
    private String authorName;
}

# Набір демонстраціних матеріалів для ООП з використанням Java

## Maven
### Основи

Використовується [maven](https://maven.apache.org/)
як найбільш популярний фреймворк для розробки професійних Java-додатків

Більш детальну інформацію можна знайти на сайті розробника вище.

Рекомендується ознайомитися із процедурою встановлення та налаштування
`maven` на таких ресурсах
* [maven tutorials](https://www.tutorialspoint.com/maven/)
* [devcolibri](https://devcolibri.com/maven-%D1%87%D0%B0%D1%81%D1%82%D1%8C-1-%D0%B7%D0%BD%D0%B0%D0%BA%D0%BE%D0%BC%D1%81%D1%82%D0%B2%D0%BE-%D0%B8-%D0%BD%D0%B0%D1%81%D1%82%D1%80%D0%BE%D0%B9%D0%BA%D0%B0/)
* [maven.ru](https://www.apache-maven.ru/)

Основою будь-якого maven проекта є файл `pom.xml`, який знаходиться у корені папки проекту.
Він дозволяє підключати до нашого проекту лише ті бібліотеки, які потрібні на різних етапах його створення.
Структура `pom.xml` є такою
```
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                    http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  .............
</project>
```
Тег `project` є головним і у нього включаються усі інші теги.

Як правило, наступними ідуть теги, які вказують до якої групи проектів належить наш проект,
його унікальний ідентифікатор та номер версії, наприклад
```
    <groupId>ua.edu.chmnu.fks</groupId>
    <artifactId>fks.oop-2k</artifactId>
    <version>0.1.0-SNAPSHOT</version>

```

Це необхідно, якщо артефакти нашого проекту будуть поміщуватися у maven repository на віддаленому сервері

Наступним як правило йде тег, який вказує на тип кінцевого артефакту.
```
 <packaging>jar</packaging>
```
Може бути `jar, war, pom, ear, maven-plugin` тощо.
Якщо тип артефакту `pom`, то це означає що даний `pom.xml` є parent file для інших проектів.

Найважливішою є секція `dependencies`, де вказується бібліотеки які потрібно завантажити та підключити до проекту.
У нашому випадку вона є такою
```
<dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>${mockito.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-core</artifactId>
            <version>1.3</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

Кожний тег `<dependency>` описує певний артефакт, який буде завантажено до локального кешу
на комп'ютері розробника та підключено до проекту.
Важливою є опція `scope`, яка вказує, на якому етапі даний артефакт буде задіяно
*`compile`  - етап компіляції;

*`provided` - у кінцевий артефакт включаються посилання на класи даної бібліотеки,
              однак вона повинна знаходитися у classpath, щоб java змогла знайти ці класи та завантажити
              на етапі виконання; якщо бібліотеку не буде знайдено, то при старті додатку виникне виключна ситуація
              `ClassNotFoundException`. Більш детально можна ознайомитися [тут](https://dzone.com/articles/java-classnotfoundexception-vs-noclassdeffounderro)

*`test` - артефакт буде задіяно лише на етапі тестування проекту, але не буде включено до цільового артефакту

За замовчуванням, артефакти завантажуються із центрального [репозиторію maven](https://mvnrepository.com/).
На ньому знаходиться величезна кількість бібліотек для різноманітних задач, що дозволяє досить швидко створювати
програми на основі перевірених та відтестованих компонентів

### Команди для управління проектом
Проект maven має наступний життєвий цикл
* **очищення** - виконується командою `mvn clean`
* **компіляція** - виконується командою `mvn compile`
* **тестування** - виконується командою `mvn test`
* **упаковування**  - виконується командою `mvn package`

Незалежними є лише перші два етапи. 3-й етап потребує компіляції проекту, а останній - компіляції та тестування.
Тому якщо тестування не було виконано артефакти не генерується, а сам етап життєвого циклу стає `FAIL`

Якщо ми хочемо пропустити етап тестування при упаковуванні проекту, необхідно виконати таку команду

`mvn clean package -DskipTests`

## Проект Lombok

Даний [проект](https://mvnrepository.com/artifact/org.projectlombok/lombok) доступний у вигляді артефактів 
на центральному репозиторії maven. Він призначений для генерації утилітарних методів класів на етапі компіляції.
Дає можливість зменшити кількість типового коду у Java-проектах.
Приклади використання наведено у пакеті `lombok`.
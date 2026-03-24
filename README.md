# 🚀 Final Project - ტესტ ავტომატიზაციის ფრეიმვორკი

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![TestNG](https://img.shields.io/badge/TestNG-6DB33F?style=for-the-badge&logo=testng&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)

---

## 📌 მიმოხილვა
ეს პროექტი არის **ტესტ ავტომატიზაციის ფრეიმვორკი**, და შექმნილია Java-ზე.  


მნიშვნელოვანი კომპონენტები:
- UI ტესტირება (Page Object Model)
- API ტესტირება
- ტესტების გაშვების და რეპორტინგის სისტემა
- კონფიგურაბელური გარემო

---

## 🧰 ტექნოლოგიები
- **Java** - პროგრამირების ენა
- **Selenium WebDriver** - ბრაუზერის ავტომატიზაციისთვის
- **TestNG** - ტესტების შესრულებისთვის
- **Maven** - პროექტის მენეჯმენტისთვის
- **Extent Reports** - რეპორტინგისთვის
- **RestAssured** - API ტესტირებისთვის

---

## 📂 პროექტის სტრუქტურა

pages - Page Object კლასები (UI ელემენტები და მოქმედებები)
tests - UI ტესტ კლასები
api - API request/response ლოგიკა
apiTests - API ტესტები
utils - დამხმარე კლასები (Driver, Config და ა.შ.)
resources - კონფიგურაციის ფაილები

## ⚙️ კონფიგურაცია

პროექტის კონფიგურაცია ხდება ფაილის მეშვეობით:

config.properties

## 📥 ინსტალაცია

რეპოზიტორის დაკლონვა:

git clone https://github.com/gugatsiklauri0402-sudo/FinalProject.git

დამოკიდებულებების დაყენება:

```mvn clean install```

---

## ▶️ ტესტების გაშვება

```mvn test```

ან

```mvn clean test```

---
## 📊 რეპორტები

Extent Report  
TestNG Report

test-output/

---

## 🔍 შესაძლებლობები

UI ტესტირება  
API ტესტირება  
Page Object Model  
Assertion-ები  
ლოგირება და რეპორტინგი

---

## 👨‍💻 ავტორი

გუგა წიკლაური  
https://github.com/gugatsiklauri0402-sudo

---

## 📜 შენიშვნა
პროექტი შექმნილია სასწავლო მიზნებისთვის.
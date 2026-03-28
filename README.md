#  Final Project - ტესტ ავტომატიზაციის ფრეიმვორკი


---

## მიმოხილვა
ეს პროექტი არის **ტესტ ავტომატიზაციის ფრეიმვორკი**, და შექმნილია Java-ზე.  


მნიშვნელოვანი კომპონენტები:
- UI ტესტირება (Page Object Model)
- API ტესტირება
- ტესტების გაშვების და რეპორტინგის სისტემა
- კონფიგურაბელური გარემო

---

##  ტექნოლოგიები
- **Java** - პროგრამირების ენა
- **Selenium WebDriver** - ბრაუზერის ავტომატიზაციისთვის
- **TestNG** - ტესტების შესრულებისთვის
- **Maven** - პროექტის მენეჯმენტისთვის
- **Extent Reports** - რეპორტინგისთვის
- **RestAssured** - API ტესტირებისთვის

---

##  პროექტის სტრუქტურა

pages - Page Object კლასები (UI ელემენტები და მოქმედებები)
tests - UI ტესტ კლასები
api - API request/response ლოგიკა
apiTests - API ტესტები
utils - დამხმარე კლასები (Driver, Config და ა.შ.)

---
##  კონფიგურაცია

პროექტის კონფიგურაცია ხდება ფაილის მეშვეობით:

config.properties

---
##  ინსტალაცია

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
##  რეპორტები

Extent Report  
TestNG Report

test-output/

---

##  შესაძლებლობები

UI ტესტირება  
API ტესტირება  
Page Object Model  
Assertion-ები  
ლოგირება და რეპორტინგი

---

##  ავტორი

გუგა წიკლაური  
https://github.com/gugatsiklauri0402-sudo

---

##  შენიშვნა
პროექტი შექმნილია სასწავლო მიზნებისთვის.
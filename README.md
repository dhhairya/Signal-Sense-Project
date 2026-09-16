# Signal-Sense: Network Mapper

> An AI-powered platform that intelligently connects volunteers with local community needs.

Signal-Sense is a two-sided platform built for the **Social Service Challenge (Intermediate Level)**. Organizations post volunteering opportunities, volunteers create profiles with their skills, interests, and availability, and a recommendation engine matches the two sides based on relevance, location, and schedule fit.

---

## ✨ Features

- **Dual Profile System** — separate, tailored profiles for volunteers (skills, interests, availability) and organizations (needs, location, schedule)
- **AI Recommendation Engine** — content-based filtering (skills/interest similarity) with a roadmap to collaborative filtering as usage data grows
- **Geo-Location Matching** — prioritizes and surfaces opportunities near the volunteer, with map-based browsing
- **In-App Communication & Scheduling** — messaging, application status tracking, reminders, and calendar sync between volunteers and organizations
- **Trust & Legitimacy Layer** — organization verification workflow and a reporting system to keep listings credible

---

## 🧩 Problem It Solves

Non-profits struggle to find the right volunteers at the right time, and volunteers struggle to find opportunities that actually fit their skills and schedules — leading to poor matches, no-shows, and burnout. Signal-Sense treats this as a **matching problem**, not just a listings board.

---

## 🏗️ Tech Stack (Java-Only)

| Layer | Tech |
|---|---|
| Backend & Frontend | Spring Boot (Spring MVC) + Thymeleaf server-rendered templates — one unified Java codebase |
| Database | PostgreSQL + PostGIS (geo-queries), via Spring Data JPA / Hibernate Spatial |
| Recommendation Engine | Java — Smile (ML library) or a custom TF-IDF/cosine-similarity `RecommendationService` |
| Maps & Geo | Google Maps Services Java Client |
| Auth | Spring Security + JJWT |
| Notifications | JavaMail API (email), Firebase Admin SDK for Java (push), Twilio Java SDK (SMS, optional) |
| Build Tool | Maven |
| Hosting | Spring Boot executable JAR, deployed via Docker on Render/Railway/AWS |

---

## 📂 Project Structure

```
signal-sense/
├── src/
│   ├── main/
│   │   ├── java/com/signalsense/
│   │   │   ├── controller/         # REST + MVC controllers
│   │   │   ├── model/               # JPA entities (User, Organization, Opportunity, Application)
│   │   │   ├── repository/          # Spring Data JPA repositories
│   │   │   ├── service/
│   │   │   │   ├── RecommendationService.java   # content-based (TF-IDF/cosine) engine
│   │   │   │   └── GeoService.java              # geo-distance / PostGIS queries
│   │   │   ├── security/            # Spring Security + JWT config
│   │   │   └── SignalSenseApplication.java
│   │   └── resources/
│   │       ├── templates/           # Thymeleaf views
│   │       ├── static/              # CSS/JS/images
│   │       └── application.properties
│   └── test/java/com/signalsense/    # unit & integration tests
├── docs/
│   └── PRD.md
├── pom.xml
└── README.md
```

---

## 🗄️ Data Model (High-Level)

- **User (Volunteer)** — profile, skills[], interests[], availability[], location, history[]
- **Organization** — profile, verification_status, location(s), ratings
- **Opportunity** — org_id, title, required_skills[], category, location, schedule, slots_available
- **Application** — user_id, opportunity_id, status, applied_at, completed_at, feedback
- **Recommendation Log** — user_id, opportunity_id, score, shown_at, clicked

---

## 🚀 Getting Started

### Prerequisites
- JDK 17+
- Maven 3.9+
- PostgreSQL (with PostGIS extension)

### Installation

```bash
# Clone the repo
git clone https://github.com/<your-username>/signal-sense.git
cd signal-sense

# Install dependencies and build
mvn clean install
```

### Environment Variables

Set these in `src/main/resources/application.properties` (or as environment variables):

```
spring.datasource.url=jdbc:postgresql://localhost:5432/signalsense
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
jwt.secret=your_jwt_secret
maps.api.key=your_google_maps_api_key
```

### Running Locally

```bash
# Run the Spring Boot app (backend + frontend + recommendation engine, all in one)
mvn spring-boot:run
```

The app will be available at `http://localhost:8080`.

---

## 🧠 How the Recommendation Engine Works

1. Volunteer and opportunity profiles are vectorized (skills + interests) using TF-IDF.
2. Cosine similarity scores volunteer-opportunity pairs.
3. Hard filters apply for geo-distance and schedule overlap.
4. Top-N ranked opportunities are surfaced on the volunteer's dashboard.
5. *(Phase 2)* Collaborative filtering layers in once enough interaction data (applications, completions, ratings) exists.

---

## 🗺️ Roadmap

- [ ] Auth + dual profile creation
- [ ] Opportunity posting + geo-filtered browsing
- [ ] Content-based recommendation engine (MVP)
- [ ] Application flow + in-app messaging
- [ ] Map view + notifications + feedback loop
- [ ] Collaborative filtering (Phase 2)

---

## 📊 Dataset

Prototyping used the [Kaggle Volunteer Opportunities Dataset](https://www.kaggle.com/datasets/volunteermatch/volunteermatch-volunteer-opportunity-data) for initial EDA and recommendation-model testing before real platform data is available.

---

## 📄 Documentation

Full product requirements are in [`docs/PRD.md`](./docs/PRD.md).

---

## 🤝 Contributing

This is a hackathon/challenge submission. Pull requests and suggestions are welcome — open an issue to discuss changes before submitting a PR.

---

## 📜 License

MIT

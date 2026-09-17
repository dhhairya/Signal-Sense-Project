# Problem Statement

Non-profits struggle to find the right volunteers at the right time, and volunteers struggle to find opportunities that actually fit their skills and schedules — leading to poor matches, no-shows, and burnout. Generic listings boards require manual searching, which is inefficient.

# Scope of the Project

Signal-Sense aims to solve this by treating volunteering as a **matching problem**. The scope of this CLI application focuses on the core interaction layer:
1. Allowing organizations to post specific needs (opportunities).
2. Allowing volunteers to declare their skills.
3. Providing an algorithmic match (Recommendation Engine) to bridge the gap automatically without manual searching.

# Target Users

1. **Volunteers**: Individuals looking to donate their time, specifically applying their unique skills (e.g., teaching, medical, logistics) to causes they care about.
2. **Organizations / NGOs**: Non-profits, schools, shelters, or event organizers who need specific help but lack the resources to manually sift through hundreds of unqualified applications.

# High-Level Features

1. **Dual Entity Management**: Robust, separate data models and workflows for Users (Volunteers) and Organizations.
2. **Opportunity Listings**: A centralized database of volunteering tasks with required skill vectors.
3. **AI Recommendation System**: A native Java implementation of TF-IDF (Term Frequency-Inverse Document Frequency) and Cosine Similarity to score and rank opportunities against a volunteer's profile.

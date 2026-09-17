package com.signalsense.signal_sense_backend;

import com.signalsense.signal_sense_backend.model.Opportunity;
import com.signalsense.signal_sense_backend.model.Organization;
import com.signalsense.signal_sense_backend.model.User;
import com.signalsense.signal_sense_backend.repository.OpportunityRepository;
import com.signalsense.signal_sense_backend.repository.OrganizationRepository;
import com.signalsense.signal_sense_backend.repository.UserRepository;
import com.signalsense.signal_sense_backend.service.OpportunityScore;
import com.signalsense.signal_sense_backend.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main command-line interface entry point for the Signal-Sense platform.
 * This class handles the interactive terminal menu for users and organizations,
 * fulfilling the core functional requirements of the project.
 */
@Component
public class CliAppRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OpportunityRepository opportunityRepository;

    @Autowired
    private RecommendationService recommendationService;

    private Scanner scanner = new Scanner(System.in);
    private User loggedInUser = null;
    private Organization loggedInOrg = null;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("==================================================");
        System.out.println("      Welcome to Signal-Sense (CLI Edition)       ");
        System.out.println("      AI-powered Volunteer Matching Platform      ");
        System.out.println("==================================================");

        // Main application loop that handles the state of logged-in users
        boolean running = true;
        while (running) {
            if (loggedInUser == null && loggedInOrg == null) {
                running = showMainMenu();
            } else if (loggedInUser != null) {
                showVolunteerMenu();
            } else {
                showOrganizationMenu();
            }
        }
        
        System.out.println("Thank you for using Signal-Sense! Goodbye.");
        System.exit(0);
    }

    private boolean showMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Register as Volunteer");
        System.out.println("2. Login as Volunteer");
        System.out.println("3. Register as Organization");
        System.out.println("4. Login as Organization");
        System.out.println("5. Exit");
        System.out.print("Select an option: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                registerVolunteer();
                return true;
            case "2":
                loginVolunteer();
                return true;
            case "3":
                registerOrganization();
                return true;
            case "4":
                loginOrganization();
                return true;
            case "5":
                return false;
            default:
                System.out.println("Invalid option. Please try again.");
                return true;
        }
    }

    private void registerVolunteer() {
        System.out.println("\n--- VOLUNTEER REGISTRATION ---");
        User user = new User();
        user.setRole("VOLUNTEER");
        
        System.out.print("Name: ");
        user.setName(scanner.nextLine());
        
        System.out.print("Email: ");
        user.setEmail(scanner.nextLine());
        
        System.out.print("Password: ");
        user.setPassword(scanner.nextLine());
        
        System.out.print("Location: ");
        user.setLocation(scanner.nextLine());
        
        System.out.print("Skills (comma-separated): ");
        String skills = scanner.nextLine();
        user.setSkills(new HashSet<>(Arrays.asList(skills.split(","))));
        
        System.out.print("Interests (comma-separated): ");
        String interests = scanner.nextLine();
        user.setInterests(new HashSet<>(Arrays.asList(interests.split(","))));
        
        userRepository.save(user);
        System.out.println("Registration successful! You can now log in.");
    }

    private void loginVolunteer() {
        System.out.println("\n--- VOLUNTEER LOGIN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(password)) {
            loggedInUser = userOpt.get();
            System.out.println("Login successful. Welcome, " + loggedInUser.getName() + "!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void showVolunteerMenu() {
        System.out.println("\n--- VOLUNTEER DASHBOARD ---");
        System.out.println("1. View AI Recommendations");
        System.out.println("2. Browse All Opportunities");
        System.out.println("3. Logout");
        System.out.print("Select an option: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                viewRecommendations();
                break;
            case "2":
                browseOpportunities();
                break;
            case "3":
                loggedInUser = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void viewRecommendations() {
        System.out.println("\n--- AI MATCHED OPPORTUNITIES ---");
        List<OpportunityScore> scores = recommendationService.getRecommendationsForUser(loggedInUser.getId());
        
        if (scores.isEmpty()) {
            System.out.println("No opportunities available right now.");
            return;
        }
        
        for (int i = 0; i < scores.size(); i++) {
            OpportunityScore os = scores.get(i);
            System.out.printf("%d. [%.2f Match] %s (at %s)\n", (i+1), os.getScore(), os.getOpportunity().getTitle(), os.getOpportunity().getLocation());
        }
    }

    private void browseOpportunities() {
        System.out.println("\n--- ALL OPPORTUNITIES ---");
        List<Opportunity> opps = opportunityRepository.findAll();
        if (opps.isEmpty()) {
            System.out.println("No opportunities found.");
            return;
        }
        for (Opportunity opp : opps) {
            System.out.println("- " + opp.getTitle() + " | Location: " + opp.getLocation() + " | Skills: " + opp.getRequiredSkills());
        }
    }

    private void registerOrganization() {
        System.out.println("\n--- ORGANIZATION REGISTRATION ---");
        Organization org = new Organization();
        org.setVerificationStatus("PENDING");
        
        System.out.print("Organization Name: ");
        org.setName(scanner.nextLine());
        
        System.out.print("Email: ");
        org.setEmail(scanner.nextLine());
        
        System.out.print("Password: ");
        org.setPassword(scanner.nextLine());
        
        System.out.print("Category (NGO, School, etc.): ");
        org.setCategory(scanner.nextLine());
        
        organizationRepository.save(org);
        System.out.println("Organization registered! You can now log in.");
    }

    private void loginOrganization() {
        System.out.println("\n--- ORGANIZATION LOGIN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        Optional<Organization> orgOpt = organizationRepository.findByEmail(email);
        if (orgOpt.isPresent() && orgOpt.get().getPassword().equals(password)) {
            loggedInOrg = orgOpt.get();
            System.out.println("Login successful. Welcome, " + loggedInOrg.getName() + "!");
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private void showOrganizationMenu() {
        System.out.println("\n--- ORGANIZATION DASHBOARD ---");
        System.out.println("1. Post a new Opportunity");
        System.out.println("2. View our Opportunities");
        System.out.println("3. Logout");
        System.out.print("Select an option: ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                postOpportunity();
                break;
            case "2":
                viewOrgOpportunities();
                break;
            case "3":
                loggedInOrg = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid option.");
        }
    }

    private void postOpportunity() {
        System.out.println("\n--- POST OPPORTUNITY ---");
        Opportunity opp = new Opportunity();
        opp.setOrganizationId(loggedInOrg.getId());
        
        System.out.print("Title: ");
        opp.setTitle(scanner.nextLine());
        
        System.out.print("Description: ");
        opp.setDescription(scanner.nextLine());
        
        System.out.print("Category: ");
        opp.setCategory(scanner.nextLine());
        
        System.out.print("Required Skills (comma-separated): ");
        String skills = scanner.nextLine();
        opp.setRequiredSkills(new HashSet<>(Arrays.asList(skills.split(","))));
        
        System.out.print("Location: ");
        opp.setLocation(scanner.nextLine());
        
        opportunityRepository.save(opp);
        System.out.println("Opportunity posted successfully!");
    }

    private void viewOrgOpportunities() {
        System.out.println("\n--- YOUR OPPORTUNITIES ---");
        List<Opportunity> opps = opportunityRepository.findByOrganizationId(loggedInOrg.getId());
        if (opps.isEmpty()) {
            System.out.println("You haven't posted any opportunities yet.");
            return;
        }
        for (Opportunity opp : opps) {
            System.out.println("- " + opp.getTitle() + " (" + opp.getLocation() + ")");
        }
    }
}

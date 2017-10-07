package com.capgemini.uas.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.service.IMacService;
import com.capgemini.uas.service.MacServiceImpl;

public class MacConsole {

	private String userName;
	private IMacService macService;
	private DateTimeFormatter format;
	private Scanner sc;
	
	public MacConsole(String userName) {
		this.userName = userName;
	}

	public void start() throws UniversityException {
		int choice, applicationId;
		String scheduledProgramId,status,doi;
		LocalDate dateOfInterview;
			macService = new MacServiceImpl();
			sc = new Scanner(System.in);
			do											//do-while loop
			{
				System.out.println("Welcome "+userName);
				System.out.println("Choose an operation");
				System.out.println("1. View list of applicants of particular program scheduled");
				System.out.println("2. View list of applicants of particular program scheduled after interview");
				System.out.println("3. Update status of applicant");
				System.out.println("4. Back to Home Page");
				System.out.println("******************************");
				System.out.print("\nPlease Enter a Choice : ");
				choice = sc.nextInt();
				System.out.println("\n******************************");
				List<ApplicationBean> applicantDetails = new ArrayList<ApplicationBean>();
				switch (choice) {
				case 1:
					System.out.println("Enter Program Scheduled Id");
					scheduledProgramId = sc.next();
					applicantDetails = macService.getApplicantsOnSchduledId(scheduledProgramId);
					 for (ApplicationBean applicants : applicantDetails) {
							System.out.println(applicants);
						}
					break;

				case 2:
					System.out.println("Enter Program Scheduled Id");
					scheduledProgramId = sc.next();
					applicantDetails = macService.getApplicantsAfterInterviewOnId(scheduledProgramId);
					for (ApplicationBean applicants : applicantDetails) {
						System.out.println(applicants);
					}
					break;
					
				case 3:
					System.out.println("Enter Applicant Id");
					applicationId = sc.nextInt();
					System.out.println("Enter Updated Status");
					status = sc.next();
					status = macService.updateApplicantStatus(applicationId, status);
					System.out.println(status);
					if(status.equals("accepted")){
						System.out.println("Please Enter Date of Interview of Accepted Applicant");
						doi = sc.next();
						format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						dateOfInterview = LocalDate.parse(doi,format);
						macService.updateApplicantDateOfInterview(applicationId, dateOfInterview);
					} 
					break;
				
				case 4:
					System.out.println("Thanks For Visiting..");
					break;
				}
}while(choice!=4);
		
	}
}

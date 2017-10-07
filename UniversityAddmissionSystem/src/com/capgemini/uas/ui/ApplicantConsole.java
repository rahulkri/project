package com.capgemini.uas.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.service.ApplicantServiceImpl;
import com.capgemini.uas.service.IApplicantService;
public class ApplicantConsole {
	
	private IApplicantService appService;
	private Scanner sc;
	private DateTimeFormatter format;
	
	public void start() throws UniversityException {
		boolean flag=true,flag1=true,flag2=true,flag3=true,flag4=true;
		String fullName,dob,highestQualification;
		LocalDate dateOfBirth = null,dateOfInterview=null;
		int marksObtained,choice;
			
			appService = new ApplicantServiceImpl();
			sc = new Scanner(System.in);
		do											//do-while loop
			{
				System.out.println("Choose an operation");
				System.out.println("1. Program Scheduled");
				System.out.println("2. Apply Online");
				System.out.println("3. Application Status");
				System.out.println("4. Exit");
				System.out.println("******************************");
				System.out.print("\nPlease Enter a Choice : ");
				choice = sc.nextInt();
				System.out.println("\n******************************");
		List<ProgramScheduledBean> programScheduled= null;
		switch (choice) {
		
		case 1:
				programScheduled = appService.showProgramScheduled();
		    for (ProgramScheduledBean pBean : programScheduled) {
				System.out.println(pBean);
			}
			break;
		case 2:
		do
		{
			System.out.println("Enter Full Name"); 
			fullName=sc.next();
			if(flag==false)
				System.out.println("Name should be entered in proper format");	
		}while(flag==false);
		do
		{
		System.out.println("Enter Date of Birth");
		dob = sc.next();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dateOfBirth = LocalDate.parse(dob,format);
		if(flag1==false)
			System.out.println("Date of Birth should be entered in proper format");
		}while(flag==false);
		
		//Customer Phone Number
		do
		{
			System.out.println("Enter your Highest Quailification : ");
			highestQualification = sc.next();
		}while(flag2==false);
		do
		{
			System.out.println("Enter your marks");
			marksObtained = sc.nextInt();
			if(flag3==false)
				System.out.println("Marks should be in proper format");
			}while(flag3==false);
		
		System.out.println("Define your goals");
		String goals = sc.next();
		System.out.println("Enter your Email Id");
		String emailId = sc.next();
		System.out.println("Please see below programs");
		
		programScheduled = appService.showProgramScheduled();
	    for (ProgramScheduledBean pBean : programScheduled) {
			System.out.println(pBean);
		}
	    String scheduledProgramId;
	    do
		{
			System.out.println("Enter Program Scheduled Id from the above table:");
			scheduledProgramId = sc.next();
		}while(flag4==false);
	    
	    ApplicationBean applicantBean = new ApplicationBean();
	    applicantBean.setFullName(fullName);
	    applicantBean.setDateOfBirth(dateOfBirth);
	    applicantBean.setHighestQualification(highestQualification);
	    applicantBean.setMarksObtained(marksObtained);
	    applicantBean.setGoals(goals);
	    applicantBean.setEmailId(emailId);
	    applicantBean.setScheduledProgramId(scheduledProgramId);
	    format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dateOfInterview = LocalDate.parse("2012-12-12",format);
	    applicantBean.setDateOfInterview(dateOfInterview);
	    int applicationId = 0;
				applicationId  = appService.addDetail(applicantBean);
	    System.out.println("Successfully applied. Your Application Id is : " + applicationId);
	   break;
	   
		case 3:
			System.out.println("Enter your Application Id");
			applicationId = sc.nextInt();
				applicantBean = appService.showStatus(applicationId);
				LocalDate checkDoi = LocalDate.parse("2012-12-12",format);
				if(applicantBean.getDateOfInterview().equals(checkDoi)){
				System.out.println("Status : "+ applicantBean.getStatus());
				}
				else{
					System.out.println("Status : "+ applicantBean.getStatus());
					System.out.println("Date of Interview : "+ applicantBean.getDateOfInterview());
				}
			
	}
			}while(choice!=4);
}
}

package com.capgemini.uas.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramOfferedBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.service.AdminServiceImpl;
import com.capgemini.uas.service.IAdminService;
import com.capgemini.uas.service.IMacService;
import com.capgemini.uas.service.MacServiceImpl;

public class AdminConsole {
	String userName;
	IAdminService service;
	IMacService macService=new MacServiceImpl();
	public AdminConsole(String userName) {
		super();
		this.userName = userName;
	}
	
	public void start() throws UniversityException {
		int choice;
		String scheduledProgramId;
		service = new AdminServiceImpl();
		Scanner sc;
			sc = new Scanner(System.in);
			do											//do-while loop
			{
				System.out.println("Welcome "+userName);
				System.out.println("Choose an operation");
				System.out.println("1. View list of applicants of particular program scheduled");
				System.out.println("2. View list of applicants of particular program scheduled after interview");
				System.out.println("3. Add program Offered");
				System.out.println("4. Delete Program Offered");
				System.out.println("5.Add Programs Scheduled");
				System.out.println("6.Delete Programs Scheduled");
				System.out.println("7.Exit");
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
					System.out.println("Enter Program offered details to add: ");
					ProgramOfferedBean pOffered=new ProgramOfferedBean() ;
					System.out.println("Enter Program Name:");
					pOffered.setProgramName(sc.next());
					System.out.println("Enter Program Description");
					pOffered.setDescription(sc.next());
					System.out.println("Enter Program Eligibilty");
					pOffered.setApplicantEligiblity(sc.next());
					System.out.println("Enter Program duration");
					pOffered.setDuration(sc.nextInt());
					System.out.println("Enter Program degree Certificate offered");
					pOffered.setDegreeCertificate(sc.next());
					service.addProgramOffered(pOffered);
					break;
				case 4:   
					
					System.out.println("Enter the program name Which you want to delete.");
					String pName=sc.next();
					boolean flag=service.deleteProgramOffered(pName);
					System.out.println("AAA");
                    if(flag==true)
                    {
                    	System.out.println("Delete Successful");
                    }
                    else
                    {
                    	System.out.println("Failed!!");
                    }
				
				break;
				case 5:
					System.out.println("Enter Program Scheduled details to add: ");
					ProgramScheduledBean pScheduled=new ProgramScheduledBean() ;
					System.out.println("Enter Program Name:");
					pScheduled.setProgramName(sc.next());
					System.out.println("Enter the New Location");
					pScheduled.setLocation(sc.next());
					System.out.println("Enter the Start Date");
					String date=sc.next();
					DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date1 = LocalDate.parse(date,formatter);
		            Date date2=java.sql.Date.valueOf(date1);
		            pScheduled.setStartDate(date2);
					System.out.println("Enter End Date");
					String edate=sc.next();
					DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate edate1 = LocalDate.parse(edate,formatter1);
		            Date edate2=java.sql.Date.valueOf(edate1);
		            pScheduled.setEndDate(edate2);
		            
		            System.out.println("Enter the number of Sessions per week");
		            pScheduled.setSessionPerWeek(sc.nextInt());
		            
					int id=service.addProgramScheduled(pScheduled);
					if(id>0)
					{
					System.out.println("The Program Added successfully with Scheduled id"+ id);
					
					}
					break;
					
				case 6:
					System.out.println("The List of Schedules is As follows:");
					List<ProgramScheduledBean> schList=service.getAllDetails();
					for(ProgramScheduledBean list:schList)
					System.out.println(list);
					
					System.out.println("Enter the Schedule ID ");
					String schId=sc.next();
					boolean flag1=service.deleteProgramScheduled(schId);
					if(flag1==true)
					{
						System.out.println("Delete Succesful");
					}
					else
					{
						System.out.println("Failed");
					}
					
			case 7:
				System.out.println("Thanks For Visiting..");
				break;

				}
				}while(choice!=7);
			
	}
}

Welcome to Pau-Escola-Bank

To start using the api,  you must first log in to get your token so that to be able to do the operations needed.

1 You have to go to /users-management/get-token and enter a valid username and password. If the credentials are correct you will get the token necessary to do all the operations.
2 Depending on the role you have assigned you will be able to perform different tasks.

	ROLES:
	-User: can only access their information and their accounts, and make transfers.
	-Moderator: can create and delete users and accounts and also modify the account information.
	-Admin: the only ones that have full permits in the app and are able to create and delete moderators.


Endpoints:
		
		-/users-management 	: Endpoints to create and delete the different types of users and to get the token.
		-/personal-space 		: These would be the "visible" endpoints, the ones that every common user(the account holders) can access by using ones token. There you only have access to your personal information, accounts and to make a transfer..

		-/api/v1/users/accountHolders: Just to get a list of all the account holders or get one by id.
		-/api/v1/users/admins : Just to get a list of all the admins or get one by id.
		
		-/api/v1/accounts/checkings : Endpoints to create, delete and update Checking Accounts.
		-/api/v1/accounts/savings : Endpoints to create, delete and update Saving Accounts.
		-/api/v1/accounts/credits : Endpoints to create, delete and update Credit Accounts.



Enpoints accessibility:

		- Public: Only have access to /users-management/get-token --> To validate their credentials and get their token
		- Users (AccountHolders) can only access /personal-space  --> To check their accounts, change their personal addresss and trnasfer money.
		- Moderators: have access to  all endpoints except for /users-management/create&&delete/admin.
		- Admins: have access to all enpoints.


Types of Accounts:

		- CREDIT: 

			# Accounts for people who want to spend now and pay at the end of the month.
			# These type of accounts have a penalty fee if your balance goes below a certain ammount.
			# After the month ends the sum of the money spent is multpiplied by the interest rate and taken from the account of the user.
			# To avoid overspending it has a credit limit.

		- SAVING:

			# Accounts for people who want to slowly save money and make it grow.
			# These type of accounts have a penalty fee if your balance goes below a certain ammount.
			# After the year ends, a formula based on the creditors nummber is performed to get the total money generated from those savings.  
		
		- CHECKINGS:

			# Typical accounts made only to store money and use it when needed.
			# There are two variants:
				· If the account holder is younger than 25 is considered a student checking account and there are no fees applied.
				· Otherwise, the account has the same penalty fee previously explained and a monthly mantainance fee.
			
      USE CASE DIAGRAM
      
      ![Use Case Diagram FINAL VERSION1](https://user-images.githubusercontent.com/107581350/193419917-b29f6ee1-0c53-4b14-90c3-43ad6d9e8638.jpg)


      CLASS DIAGRAM
      
      ![Class Diagram](https://user-images.githubusercontent.com/107581350/193419953-c81e6fe9-9035-4719-95b0-d1f72fc837b3.jpg)

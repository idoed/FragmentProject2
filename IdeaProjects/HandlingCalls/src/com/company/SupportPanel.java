package com.company;
import com.company.model.Call;
import com.company.model.Employee;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class SupportPanel {
    private static int i,mCallsId=0;
    private static ArrayList<Employee> mEmployeesLevel1,mEmployeesLevel2;
    private static Employee mManager;
    int num1,num2;

   private Queue<Call> mQueue= new LinkedList<>();
   private Queue<Call> mPriorityQueue=new LinkedList<>();
//    private static SupportPanel instance=null;
//    public static SupportPanel getInstance(){
//        if (instance==null){
//            instance=new SupportPanel();
//        }
//        return instance;
//    }

    public SupportPanel(int mNum1, int mNum2) {
        this.num1 = mNum1;
        this.num2 = mNum2;
    }

    //Creating new Employee's and put them in Arraylist According to their Level.
    public void settingUpEmployers(){
        mEmployeesLevel1=new ArrayList<>();
        mEmployeesLevel2=new ArrayList<>();
        //Setting the level 1 Employees
        for ( i = 0; i <num1 ; i++) {
            Employee employee=new Employee(1,true);
            mEmployeesLevel1.add(employee);
        }//Setting the level 2 Employees
        for (int j = 0; j <num2 ; j++) {
            Employee employee=new Employee(2,true);
            i++;
            mEmployeesLevel2.add(employee);
        }
        //Setting the Manager
        mManager=new Employee(3,true);
    }
    /**
     * Getting new Call and put it in our Array.
     * @param calls
     */
    public void HandleNewCall(Call calls){
        calls.setSolve(false);
        calls.setId(mCallsId);
        mCallsId++;
        mQueue.add(calls);
    }
    public void AllocateCallsToEmployees() {
        Call call = null;
        if (mPriorityQueue != null) {
            call = mPriorityQueue.poll();
        } else if (mQueue != null) {
            call = mQueue.poll();
        } else {
            System.out.println("You have to Calls to Treat");
            return;
        }
        for (int j = 0; j < mEmployeesLevel1.size(); j++) {
            Employee employee = mEmployeesLevel1.get(j);
            if (employee.getAvailable()) {
                employee.setAvailable(false);
                //setting the Employee as busy
                mEmployeesLevel1.set(j, employee);
                /**If the User Solve The Problem i call the methods-
                // call.setSolve(true);
                //employee.setAvailable(true);
                 and if not i still need to make the employee available
                //else
                // employee.setAvailable(true);
                 */

                j = mEmployeesLevel1.size();
                //Exit from the For Loop and Prepare the Level2 Support Array;
            }
        }
        if(!call.getSolve()){
            //if the Call still unsolved than go to the next Group
            checkingNextGroup(call);
        }
    }

        public void checkingNextGroup(Call call){
            for (int j=0;j<mEmployeesLevel2.size();j++){
                Employee employee=mEmployeesLevel2.get(j);
                if(employee.getAvailable()){
                    employee.setAvailable(false);
                    mEmployeesLevel2.set(j,employee);
                  /**If the user solve the Problem i call the methods-
                    // call.setSolve(true);
                    //employee.setAvailable(true);
                    //else
                  employee.setAvailable(true);
                   if(mManager.getmAvailable()) {
                   The Manager Recieiving the problem and fix it
                 call.setSolve(true);
                   /*/
                  }
                    j=mEmployeesLevel2.size();
                }
            if(!call.getSolve()){
                mPriorityQueue.add(call);
                System.out.println("The Call moved to the priority queue and will get Employee soon as possible");
            }
        }
    }


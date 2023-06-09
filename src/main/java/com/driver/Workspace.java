package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar=new ArrayList<>(); // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        int count=0;
        if(calendar.size()==0)return 0;
        if(calendar.size()==1)return 1;
        Collections.sort(calendar, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
               int res= o1.getEndTime().compareTo(o2.getEndTime());
               if(res==0)
               {
                   return  o1.getStartTime().compareTo(o2.getStartTime());
               }
               return res;
            }
        });
        count=1;  LocalTime curr=calendar.get(0).getEndTime();
        for(int i=1;i<calendar.size();i++)
        {
          Meeting meet=calendar.get(i);
          LocalTime start=meet.getStartTime();
          LocalTime end=meet.getEndTime();
          if(curr.compareTo(start)<0)
          {
              count++;
              curr=end;
          }
        }
        return count;
    }
}

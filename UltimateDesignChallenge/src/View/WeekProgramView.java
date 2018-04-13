package View;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import Entry.Entries;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class WeekProgramView extends JPanel implements WeekView, ObserverView{
	
	CalendarView cv;
	DayView[] dvs;

	//private List<Integer> rowAssignment;
	//private int currentRow;	
	
	JScrollPane scrollCalendarTable;    
    JPanel dayViewsPanel;
    
    public void deselect() {
    	
    }
    
    public void updateCurrent(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
    	for(int i = 0 ; i < 7 ; i++) {
    		dvs[i].updateCurrent(currentSelectedYear, currentSelectedMonth, currentSelectedDay+i);
    	}
    }
    
    // WHERE YOU WILL IMPLEMENT HOW THE DATA IS DISPLAYED
    public void update(Entries slots, Entries books) {
    	for(int i = 0 ; i < 7 ; i++) {
    		((ObserverView) dvs[i]).update(slots,books);
    	}    	
    }
    
	public WeekProgramView(CalendarView cv){
		
		setBounds(0,0,633,453);
		
		this.cv = cv;
		
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}
                
		scrollCalendarTable = new JScrollPane();
		scrollCalendarTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollCalendarTable, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollCalendarTable, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		dayViewsPanel = new JPanel();
		scrollCalendarTable.setViewportView(dayViewsPanel);
		dayViewsPanel.setLayout(new GridLayout(0, 7, 0, 0));
		setLayout(groupLayout);
		
		dvs = new DayView[7];
		for(int i = 0 ; i < 7 ; i++) {
			dvs[i] = new DayProgramView(cv);
			dayViewsPanel.add((Component) dvs[i]);
		}
		
	}
}
=======
package View;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import Entry.Occasion;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class WeekProgramView extends JPanel implements WeekView, ObserverView{
	
	CalendarView cv;
	DayView[] dvs;

	//private List<Integer> rowAssignment;
	//private int currentRow;	
	
	JScrollPane scrollCalendarTable;    
    JPanel dayViewsPanel;
    
    public void deselect() {
    	
    }
    
    public void updateCurrent(int currentSelectedYear, int currentSelectedMonth, int currentSelectedDay) {
    	for(int i = 0 ; i < 7 ; i++) {
    		dvs[i].updateCurrent(currentSelectedYear, currentSelectedMonth, currentSelectedDay+i);
    	}
    }
    
    public void update(List<Occasion>occasions) {
    	for(int i = 0 ; i < 7 ; i++) {
    		((ObserverView) dvs[i]).update(occasions);
    	}    	
    }
    
	public WeekProgramView(CalendarView cv){
		
		setBounds(0,0,633,453);
		
		this.cv = cv;
		
		try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                }
		catch (Exception e) {}
                
		scrollCalendarTable = new JScrollPane();
		scrollCalendarTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollCalendarTable, GroupLayout.DEFAULT_SIZE, 428, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollCalendarTable, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		dayViewsPanel = new JPanel();
		scrollCalendarTable.setViewportView(dayViewsPanel);
		dayViewsPanel.setLayout(new GridLayout(0, 7, 0, 0));
		setLayout(groupLayout);
		
		dvs = new DayView[7];
		for(int i = 0 ; i < 7 ; i++) {
			dvs[i] = new DayProgramView(cv);
			dayViewsPanel.add((Component) dvs[i]);
		}
		
	}
}

package com.daellhin.realisticsolar.gui.machines;

import com.daellhin.realisticsolar.tile.base.TileThreeSlotMachine;

public class GuiScript {
    private static String Progress = null;
    
    public static String Arrow(TileThreeSlotMachine tile ) {
	int i1 = tile.getProgressScaled(50);
	
	if(i1*2 <= 0) {Progress = "";}
	else if(i1*2 > 90) {Progress = "----->";}
	else if(i1*2 > 75) {Progress = "-----";}
	else if(i1*2 > 60) {Progress = "----";}
	else if(i1*2 > 45) {Progress = "---";}
	else if(i1*2 > 30) {Progress = "--";}
	else if(i1*2 > 15) {Progress = "-";}
	
	return Progress;
    }
    
    public static int Center(int n) {
	if(n >= 100) {return 0;}
	else if(n < 100 && n >  9) {return 6;}
	else if(n < 9   && n >= 0) {return 12;}  
	else{return 0;}
    }

}

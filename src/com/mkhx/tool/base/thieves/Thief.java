package com.mkhx.tool.base.thieves;

public class Thief {
	public String Uid = "";
	public String NickName = "";
	public String Avatar = "";
	public String ThievesId = "";
	public String Time = "";
	public String Status = "";
	public String HPCount = "";
	public String HPCurrent = "";
	public String Type = "";
	public String FleeTime = "";
	public String UserThievesId = "";

	public int getThiefProfile() {
		try {
			int retProfile = 0;
			//贼的等级
			int thiefLv = Integer.parseInt(ThievesId);
			//贼的总血量
			int hpTotal = Integer.parseInt(this.HPCount);
			//贼的剩余血量
			int hpLeft  = Integer.parseInt(this.HPCurrent);
			//贼的剩余时间
			int timeLeft = Integer.parseInt(this.FleeTime);
			//不同等级的贼，返回的可能会不一样
			switch (thiefLv/10) {
			case 10:
				retProfile += 1*80;
				break;
			case 9:
				retProfile += 1*80;
				break;
			case 8:
				retProfile += 2*80;
				break;
			case 7:
				retProfile += 3*80;
				break;
			case 6:
				retProfile += 4*70;
				break;
			case 5:
				retProfile += 4*75;
				break;
			case 4:
				retProfile += 4*80;
				break;
			default:
				retProfile += 1*70;
				break;
			}
			//总血量越高，证明有可能是精英贼，可能会越高
			switch(hpTotal / 10000){
			case 1:
				retProfile += 20;
				break;
			case 2:
				retProfile += 30;
				break;
			case 3:
				retProfile += 40;
				break;
			case 4:
				retProfile += 80;
				break;
			default:
				retProfile += 120;
				break;
			}
			//剩余血量越低，证明该贼有较高的机率被打死，权值越高
			switch(hpLeft / 5000){
			case 0:
				retProfile += 600;
				break;
			case 1:
				retProfile += 380;
				break;
			case 2:
				retProfile += 300;
				break;
			case 3:
				retProfile += 150;
				break;
			case 4:
				retProfile += 80;
				break;
			case 5:
				retProfile += 40;
				break;
			default:
				retProfile += 0;
				break;
			}
			double timeRemind = 0.0;
			//剩余时间越长，证明该贼有较高的机率被打死，权值越高
			if(timeLeft/60 < 0){
				timeRemind = 0;
			}else if (timeLeft/60 <= 5 ){
				timeRemind = 0.12;
			}else if( timeLeft/60 <=10 ){
				timeRemind = 0.28;
			}else if( timeLeft/60 < 15 ){
				timeRemind = 0.4;
			}else if( timeLeft/60 < 20 ){
				timeRemind = 0.5;
			}else if( timeLeft/60 < 30 ){
				timeRemind = 0.6;
			}else if( timeLeft/60 < 60 ){
				timeRemind = 0.75;
			}else{
				timeRemind = 1;
			}
			return (int) (retProfile * timeRemind);
		} catch (Exception e) {
			return 0;
		}
	}
}

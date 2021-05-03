package ac.echo.core.adapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.entity.Player;

import ac.echo.core.EchoCore;
import io.github.thatkawaiisam.assemble.AssembleAdapter;

public class EchoAssembleAdapter implements AssembleAdapter {
    EchoCore echoCore;
    
    public EchoAssembleAdapter(EchoCore echoCore){
        this.echoCore = echoCore;
    }

	@Override
	public String getTitle(Player player) {
		if(echoCore.getSidebarManager().getSidebar(player) != null){
			return echoCore.getSidebarManager().getSidebar(player).getLeft();
		}else{
			return "none?";	
		}
		//return (echoCore.getSidebarManager().hasSidebar(player) ? echoCore.getSidebarManager().getSidebar(player).getLeft() : "none?");
	}

	@Override
	public List<String> getLines(Player player) {
		if(echoCore.getSidebarManager().getSidebar(player) != null){
			return echoCore.getSidebarManager().getSidebar(player).getMiddle();
		}else{
			return Arrays.asList("");
		}
		//return (echoCore.getSidebarManager().hasSidebar(player) ? echoCore.getSidebarManager().getSidebar(player).getMiddle() : Arrays.asList(""));
	}
}

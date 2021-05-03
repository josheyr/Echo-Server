package ac.echo.practice.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ac.echo.core.classes.EchoPlayer;
import ac.echo.practice.EchoPractice;
import ac.echo.practice.classes.Party;
import ac.echo.practice.managers.PartyManager;

public class PartyCommand implements CommandExecutor {
    EchoPractice echoPractice;
    PartyManager partyManager;
    
    public PartyCommand(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
        partyManager = echoPractice.getPartyManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player)sender;
            EchoPlayer rp = echoPractice.getEchoPlayerManager().getEchoPlayer(p.getUniqueId());

            if(args.length > 0){
                switch(args[0]){
                    case "join":
                        return joinCommand(rp, args);
                    case "invite":
                        return inviteCommand(rp, args);
                    case "create":
                        return createCommand(rp, args);
                    case "disband":
                        return joinCommand(rp, args);
                    case "kick":
                        return kickCommand(rp, args);
                    case "leave":
                        return leaveCommand(rp, args);
                    case "open":
                        return openCommand(rp, args);
                    case "limit":
                        return limitCommand(rp, args);
                    case "list":
                        return listCommand(rp, args);
                    default:
                        return inviteCommand(rp, new String[]{ "invite", args[0] });
                }
            }else{
                helpCommand(rp, args);
            }
        }

        return true;
    }

    public boolean listCommand(EchoPlayer p, String[] args){
        if(args.length > 1){
            Player rp = Bukkit.getPlayer(args[1]);
            if(rp != null){
                EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(rp.getUniqueId());
                Party party = partyManager.getParty(ep);

                if(party != null){
                    p.getPlayer().sendMessage("Party member list:");
                    p.getPlayer().sendMessage("- " + party.getLeader().getPlayer().getDisplayName() + " - leader");

                    for(EchoPlayer member : party.getMembers()){
                        p.getPlayer().sendMessage("- " + member.getPlayer().getDisplayName() + "");
                    }

                }else{
                    p.getPlayer().sendMessage("That player is not in a party.");
                }
            }else{
                p.getPlayer().sendMessage("Player " + args[1] + " not found!");
            }
        }else{
            Party party = partyManager.getParty(p);

            if(party != null){
                p.getPlayer().sendMessage("Party member list:");
                p.getPlayer().sendMessage("- ✫ " + party.getLeader().getPlayer().getDisplayName());

                for(EchoPlayer member : party.getMembers()){
                    p.getPlayer().sendMessage("- " + member.getPlayer().getDisplayName() + "");
                }
            }else{
                p.getPlayer().sendMessage("You must be in a party to use this command.");
            }
        }

        return true;
    }

    public boolean joinCommand(EchoPlayer p, String[] args){
        if(args.length > 1){
            if(partyManager.getParty(p) == null){
                Player rp = Bukkit.getPlayer(args[1]);
                if(rp != null){
                    EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(rp.getUniqueId());

                    Party party = partyManager.getParty(ep);
                    if(party != null){
                        if(!party.isFull()){
                            if(party.isOpen()){
                                if(!partyManager.addToParty(p, party)){
                                    p.getPlayer().sendMessage("You cannot join this party right now.");
                                }
                            }else{
                                if(party.isInvited(p)){
                                    if(!partyManager.addToParty(p, party)){
                                        p.getPlayer().sendMessage("You cannot join this party right now.");
                                    }
                                }else{
                                    p.getPlayer().sendMessage("You are not invited to this party.");
                                }
                            }
                        }else{
                            p.getPlayer().sendMessage("This party is full.");
                        }
                    }else{
                        p.getPlayer().sendMessage("This player is not in a party.");
                    }
                }else{
                    p.getPlayer().sendMessage("Player " + args[1] + " not found!");
                }
            }else{
                p.getPlayer().sendMessage("You must first leave your current party before joining a new one.");
            }
        }else{
            p.getPlayer().sendMessage("args: /party join <player>");
        }

        return false;
    }

    public boolean inviteCommand(EchoPlayer p, String[] args){
        if(args.length > 1){
            Party party = partyManager.getParty(p);

            if(party != null){
                Player rp = Bukkit.getPlayer(args[1]);
                if(rp != null){
                    EchoPlayer ep = echoPractice.getEchoPlayerManager().getEchoPlayer(rp.getUniqueId());

                    Party rparty = partyManager.getParty(ep);
                    if(rparty == null){
                        if(!party.isInvited(ep)){
                            if(party.isAllinvite()){
                                if(partyManager.inviteToParty(ep, party, p)){
                                }else{
                                    p.getPlayer().sendMessage("This player couldn't be invited to the party.");
                                }
                            }else{
                                if(party.getLeader() == p){
                                    if(!partyManager.inviteToParty(ep, party, p)){
                                        p.getPlayer().sendMessage("This player couldn't be invited to the party.");
                                    }
                                }else{
                                    p.getPlayer().sendMessage("You must be leader of the party to invite.");
                                }
                            }
                        }else{
                            p.getPlayer().sendMessage("You've already invited this player.");
                        }
                    }else{
                        p.getPlayer().sendMessage("This player is already in a party.");
                    }
                }else{
                    p.getPlayer().sendMessage("Player " + args[1] + " not found!");
                }
            }else{

                createCommand(p, args);
                
                inviteCommand(p, args);
            }
        }else{
            p.getPlayer().sendMessage("args: /party invite <player>");
        }

        return false;
    }

    public boolean createCommand(EchoPlayer p, String[] args){
        Party party = partyManager.getParty(p);
        if(party == null){
            partyManager.createParty(p);
        }else{
            p.getPlayer().sendMessage("You must first leave your current party before creating a new one.");
        }

        return false;
    }

    public boolean disbandCommand(EchoPlayer p, String[] args){

        return false;
    }

    public boolean kickCommand(EchoPlayer p, String[] args){

        return false;
    }

    public boolean leaveCommand(EchoPlayer p, String[] args){
        Party party = partyManager.getParty(p);
        if(party != null){
            if(party.getLeader() == p){

            }else{
                
            }
        }else{
            p.getPlayer().sendMessage("You must be in a party to use this command.");
        }
        return false;
    }

    public boolean optionsCommand(EchoPlayer p, String[] args){

        return false;
    }

    public boolean openCommand(EchoPlayer p, String[] args){

        return false;
    }

    public boolean helpCommand(EchoPlayer p, String[] args){
        p.getPlayer().sendMessage(
            "\nParty commands:" +
            "\n- /party create - Create a new party." +
            "\n- /party invite <player> - Invite player to your party." +
            "\n- /party invites - List current party invites / parties you're invited to." +
            "\n- /party join <player> - Join a party." +
            "\n- /party list [player] - List members in your party." +
            "\n- /party chat <message> - Send message to party." +
            "\n- /party leave - Leave current party." +
            "\n- /party promote <player> - Give player leadership of party." +
            "\n- /party kick <player> - Kick player from party." +
            "\n- /party limit <amount> - Limit amount of party members." +
            "\n- /party open - Allow anyone to join party." +
            "\n- /party disband - Disband party."
            );

        return false;
    }

    public boolean limitCommand(EchoPlayer p, String[] args){

        return false;
    }
}

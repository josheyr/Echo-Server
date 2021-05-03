package ac.echo.practice.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import ac.echo.practice.EchoPractice;

public class ProjectileLaunchEventHandler implements Listener {

    EchoPractice echoPractice;

    public ProjectileLaunchEventHandler(EchoPractice echoPractice){
        this.echoPractice = echoPractice;
    }

    @EventHandler
    void onProjectileLaunch(ProjectileLaunchEvent e) {
        if (e.getEntityType() == EntityType.SPLASH_POTION) {
            Projectile projectile = e.getEntity();

            if (projectile.getShooter() instanceof Player && ((Player) projectile.getShooter()).isSprinting()) {
                Vector velocity = projectile.getVelocity();

                velocity.setY(velocity.getY() - 0.5);
                projectile.setVelocity(velocity);
            }
        }
    }
}

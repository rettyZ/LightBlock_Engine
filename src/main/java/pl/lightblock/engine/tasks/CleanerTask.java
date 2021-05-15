package pl.lightblock.engine.tasks;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.*;

public class CleanerTask {
    public static void clearItems(){
        World[] worlds = Bukkit.getServer().getWorlds().toArray(new World[0]);
        World[] array;

        for(int length = (array = worlds).length, i = 0; i < length; i++){
            World world = array[i];

            for(Entity e : world.getEntities()){
                if(!(e instanceof Item)
                        && !(e instanceof Boat)
                        && !(e instanceof Minecart)
                        && !(e instanceof ExperienceOrb)
                        && !(e instanceof Painting)
                        && !(e instanceof Snowball)
                        && !(e instanceof TNTPrimed)) {
                    continue;
                }
                e.remove();
                }
            }
        }
    }

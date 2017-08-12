/***********************************************************************************
* Copyright (c) 2015 /// Project SWG /// www.projectswg.com                        *
*                                                                                  *
* ProjectSWG is the first NGE emulator for Star Wars Galaxies founded on           *
* July 7th, 2011 after SOE announced the official shutdown of Star Wars Galaxies.  *
* Our goal is to create an emulator which will provide a server for players to     *
* continue playing a game similar to the one they used to play. We are basing      *
* it on the final publish of the game prior to end-game events.                    *
*                                                                                  *
* This file is part of Holocore.                                                   *
*                                                                                  *
* -------------------------------------------------------------------------------- *
*                                                                                  *
* Holocore is free software: you can redistribute it and/or modify                 *
* it under the terms of the GNU Affero General Public License as                   *
* published by the Free Software Foundation, either version 3 of the               *
* License, or (at your option) any later version.                                  *
*                                                                                  *
* Holocore is distributed in the hope that it will be useful,                      *
* but WITHOUT ANY WARRANTY; without even the implied warranty of                   *
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                    *
* GNU Affero General Public License for more details.                              *
*                                                                                  *
* You should have received a copy of the GNU Affero General Public License         *
* along with Holocore.  If not, see <http://www.gnu.org/licenses/>.                *
*                                                                                  *
***********************************************************************************/
package resources.commands.callbacks;

import com.projectswg.common.data.location.Location;
import com.projectswg.common.data.location.Terrain;

import intents.chat.SystemMessageIntent;
import intents.object.ObjectTeleportIntent;
import resources.commands.ICmdCallback;
import resources.objects.SWGObject;
import resources.player.Player;
import services.galaxy.GalacticManager;
import services.objects.ObjectManager.ObjectLookup;

public class AdminTeleportCallback implements ICmdCallback {

	@Override
	public void execute(GalacticManager galacticManager, Player player, SWGObject target, String args) {
		String [] cmd = args.split(" ");
		if (cmd.length < 4 || cmd.length > 5) {
			SystemMessageIntent.broadcastPersonal(player, "Wrong Syntax. For teleporting yourself, command has to be: /teleport <planetname> <x> <y> <z>");
			SystemMessageIntent.broadcastPersonal(player, "For teleporting another player, command has to be: /teleport <charname> <planetname> <x> <y> <z>");
			return;
		}
		double x, y, z;
		int cmdOffset = 0;
		if (cmd.length > 4)
			cmdOffset = 1; 
		try {
			x = Double.parseDouble(cmd[cmdOffset+1]);
			y = Double.parseDouble(cmd[cmdOffset+2]);
			z = Double.parseDouble(cmd[cmdOffset+3]);
		} catch (NumberFormatException e) {
			SystemMessageIntent.broadcastPersonal(player, "Wrong Syntax or Value. Please enter the command like this: /teleport <planetname> <x> <y> <z>");
			return;
		}
		
		Terrain terrain = Terrain.getTerrainFromName(cmd[cmdOffset]);
		if (terrain == null) {
			SystemMessageIntent.broadcastPersonal(player, "Wrong Syntax or Value. Invalid terrain: " + cmd[cmdOffset]);
			return;
		}
		
		SWGObject teleportObject = player.getCreatureObject();
		if (cmd.length > 4) {
			long characterId = galacticManager.getPlayerManager().getCharacterIdByFirstName(cmd[0]);
			if (characterId == 0) {
				SystemMessageIntent.broadcastPersonal(player, "Invalid character name: '"+cmd[0]+"'");
				return;
			}
			teleportObject = ObjectLookup.getObjectById(characterId);
			if (teleportObject == null) {
				SystemMessageIntent.broadcastPersonal(player, "Server Error. Unable to lookup creature with id: " + characterId);
				return;
			}
		}
		
		ObjectTeleportIntent.broadcast(teleportObject, new Location(x, y, z, terrain));
	}

}

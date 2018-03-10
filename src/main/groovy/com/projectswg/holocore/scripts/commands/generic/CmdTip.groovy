/***********************************************************************************
 * Copyright (c) 2018 /// Project SWG /// www.projectswg.com                       *
 *                                                                                 *
 * ProjectSWG is the first NGE emulator for Star Wars Galaxies founded on          *
 * July 7th, 2011 after SOE announced the official shutdown of Star Wars Galaxies. *
 * Our goal is to create an emulator which will provide a server for players to    *
 * continue playing a game similar to the one they used to play. We are basing     *
 * it on the final publish of the game prior to end-game events.                   *
 *                                                                                 *
 * This file is part of Holocore.                                                  *
 *                                                                                 *
 * --------------------------------------------------------------------------------*
 *                                                                                 *
 * Holocore is free software: you can redistribute it and/or modify                *
 * it under the terms of the GNU Affero General Public License as                  *
 * published by the Free Software Foundation, either version 3 of the              *
 * License, or (at your option) any later version.                                 *
 *                                                                                 *
 * Holocore is distributed in the hope that it will be useful,                     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of                  *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                   *
 * GNU Affero General Public License for more details.                             *
 *                                                                                 *
 * You should have received a copy of the GNU Affero General Public License        *
 * along with Holocore.  If not, see <http://www.gnu.org/licenses/>.               *
 ***********************************************************************************/

package com.projectswg.holocore.scripts.commands.generic

import com.projectswg.holocore.resources.objects.SWGObject
import com.projectswg.holocore.resources.player.AccessLevel
import com.projectswg.holocore.resources.player.Player
import com.projectswg.holocore.services.galaxy.GalacticManager
import com.projectswg.holocore.utilities.IntentFactory

static def execute(GalacticManager galacticManager, Player player, SWGObject target, String args) {
	if (player.getAccessLevel() == AccessLevel.PLAYER) {
		IntentFactory.sendSystemMessage(player, "Unable to access /tip command - currently reserved for admins")
		return
	}
	def argSplit = args.split(" ")
	if (argSplit.length < 2) {
		IntentFactory.sendSystemMessage(player, "Invalid Arguments: " + args)
		return
	}
	def creature = player.getCreatureObject()
	if (argSplit[0] == "bank")
		creature.setBankBalance(creature.getBankBalance() + Long.valueOf(argSplit[1]))
	else if (argSplit[0] == "cash")
		creature.setCashBalance(creature.getCashBalance() + Long.valueOf(argSplit[1]))
	else
		IntentFactory.sendSystemMessage(player, "Unknown Destination: " + argSplit[0])
}

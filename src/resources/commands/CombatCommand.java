/************************************************************************************
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
package resources.commands;

import java.util.HashMap;
import java.util.Map;

import resources.combat.AttackType;
import resources.combat.DamageType;
import resources.combat.ValidTarget;
import resources.objects.weapon.WeaponType;

public class CombatCommand extends Command {
	
	private ValidTarget validTarget;
	private boolean forceCombat;
	private Map<WeaponType, String[]> animations;
	private String [] defaultAnimation;
	private AttackType attackType;
	private double healthCost;
	private double actionCost;
	private DamageType damageType;
	private boolean ignoreDistance;
	private boolean pvpOnly;
	private int attackRolls;
	
	public CombatCommand(String name) {
		super(name);
		animations = new HashMap<>();
	}
	
	public ValidTarget getValidTarget() {
		return validTarget;
	}
	
	public boolean isForceCombat() {
		return forceCombat;
	}
	
	public AttackType getAttackType() {
		return attackType;
	}
	
	public double getHealthCost() {
		return healthCost;
	}
	
	public double getActionCost() {
		return actionCost;
	}
	
	public DamageType getDamageType() {
		return damageType;
	}
	
	public boolean isIgnoreDistance() {
		return ignoreDistance;
	}
	
	public boolean isPvpOnly() {
		return pvpOnly;
	}
	
	public int getAttackRolls() {
		return attackRolls;
	}
	
	public String [] getDefaultAnimations() {
		return defaultAnimation;
	}
	
	public String getRandomAnimation(WeaponType type) {
		String [] animations = this.animations.get(type);
		if (animations == null || animations.length == 0)
			animations = defaultAnimation;
		if (animations == null || animations.length == 0)
			return "";
		return animations[(int) (Math.random() * animations.length)];
	}
	
	public void setValidTarget(ValidTarget validTarget) {
		this.validTarget = validTarget;
	}
	
	public void setForceCombat(boolean forceCombat) {
		this.forceCombat = forceCombat;
	}
	
	public void setAttackType(AttackType attackType) {
		this.attackType = attackType;
	}
	
	public void setHealthCost(double healthCost) {
		this.healthCost = healthCost;
	}
	
	public void setActionCost(double actionCost) {
		this.actionCost = actionCost;
	}
	
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	
	public void setIgnoreDistance(boolean ignoreDistance) {
		this.ignoreDistance = ignoreDistance;
	}
	
	public void setPvpOnly(boolean pvpOnly) {
		this.pvpOnly = pvpOnly;
	}
	
	public void setAttackRolls(int attackRolls) {
		this.attackRolls = attackRolls;
	}
	
	public void setDefaultAnimation(String [] animations) {
		this.defaultAnimation = animations;
	}
	
	public void setAnimations(WeaponType type, String [] animations) {
		this.animations.put(type, animations);
	}
	
}
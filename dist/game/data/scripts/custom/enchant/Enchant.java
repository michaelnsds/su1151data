package custom.enchant;

import java.util.logging.Logger;

import l2r.gameserver.model.actor.L2Npc;
import l2r.gameserver.model.actor.instance.L2PcInstance;
import l2r.gameserver.model.entity.olympiad.OlympiadManager;
import l2r.gameserver.model.itemcontainer.Inventory;
import l2r.gameserver.model.items.instance.L2ItemInstance;
import l2r.gameserver.model.quest.Quest;
import l2r.gameserver.model.quest.QuestState;
import l2r.gameserver.network.serverpackets.CharInfo;
import l2r.gameserver.network.serverpackets.ExBrExtraUserInfo;
import l2r.gameserver.network.serverpackets.InventoryUpdate;
import l2r.gameserver.network.serverpackets.UserInfo;

public class Enchant extends Quest
{
	public static final Logger _log = Logger.getLogger(Enchant.class.getName());
	
	private final static int npcId = 9994;
	
	// Item required to enchant armor +1
	private final static int itemRequiredArmor = 22574;
	private final static int itemRequiredArmorCount = 5;
	
	// Item required to enchant jewels +1
	private final static int itemRequiredJewels = 22574;
	private final static int itemRequiredJewelsCount = 5;
	
	// Item required to enchant weapon +1
	private final static int itemRequiredWeapon = 22574;
	private final static int itemRequiredWeaponCount = 10;
	
	// Item required to enchant belt/shirt +1
	private final static int itemRequiredBeltShirt = 22574;
	private final static int itemRequiredBeltShirtCount = 5;
	
	public Enchant(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		addStartNpc(npcId);
		addFirstTalkId(npcId);
		addTalkId(npcId);
	}
	
	public static void main(String[] args)
	{
		new Enchant(-1, Enchant.class.getSimpleName(), "custom");
	}
	
	@Override
	public String onFirstTalk(L2Npc npc, L2PcInstance player)
	{
		String enchantType = "Enchant.htm";
		
		if (player.getQuestState(getName()) == null)
		{
			newQuestState(player);
		}
		else if (player.isInCombat())
		{
			return drawHtml("You are in combat", "Don't fight if you want to talk with me!", enchantType);
		}
		else if (player.getPvpFlag() == 1)
		{
			return drawHtml("You are flagged", "Don't fight if you want to talk with me!", enchantType);
		}
		else if (player.getKarma() != 0)
		{
			return drawHtml("You are in chaotic state", "Don't fight if you want to talk with me!", enchantType);
		}
		else if (OlympiadManager.getInstance().isRegistered(player))
		{
			return drawHtml("You are registered for Olympiad", "You can't use my services<br1>while playing the Olympiad.", enchantType);
		}
		
		return "Enchant.htm";
	}
	
	@Override
	public String onAdvEvent(String event, L2Npc npc, L2PcInstance player)
	{
		String htmlText = event;
		
		String enchantType = "Enchant.htm";
		
		int armorType = -1;
		
		// Armor parts
		if (event.equals("enchantHelmet1") || event.equals("enchantHelmet10") || event.equals("enchantHelmet50"))
		{
			armorType = Inventory.PAPERDOLL_HEAD;
			enchantType = "EnchantArmor.htm";
			
			switch (htmlText)
			{
				case "enchantHelmet1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantHelmet10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantHelmet50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				
			}
			
		}
		else if (event.equals("enchantChest1") || event.equals("enchantChest10") || event.equals("enchantChest50"))
		{
			armorType = Inventory.PAPERDOLL_CHEST;
			enchantType = "EnchantArmor.htm";
			
			switch (htmlText)
			{
				case "enchantChest1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantChest10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantChest50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				
			}
		}
		else if (event.equals("enchantLeggings1") || event.equals("enchantLeggings10") || event.equals("enchantLeggings50"))
		{
			armorType = Inventory.PAPERDOLL_LEGS;
			enchantType = "EnchantArmor.htm";
			
			switch (htmlText)
			{
				case "enchantLeggings1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantLeggings10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantLeggings50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				
			}
		}
		else if (event.equals("enchantGloves1") || event.equals("enchantGloves10") || event.equals("enchantGloves50"))
		{
			armorType = Inventory.PAPERDOLL_GLOVES;
			enchantType = "EnchantArmor.htm";
			
			switch (htmlText)
			{
				case "enchantGloves1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantGloves10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantGloves50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				
			}
		}
		else if (event.equals("enchantBoots1") || event.equals("enchantBoots10") || event.equals("enchantBoots50"))
		{
			armorType = Inventory.PAPERDOLL_FEET;
			enchantType = "EnchantArmor.htm";
			
			switch (htmlText)
			{
				case "enchantBoots1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantBoots10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantBoots50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				
			}
		}
		else if (event.equals("enchantShieldOrSigil1") || event.equals("enchantShieldOrSigil10") || event.equals("enchantShieldOrSigil50"))
		{
			armorType = Inventory.PAPERDOLL_LHAND;
			enchantType = "EnchantArmor.htm";
			
			switch (htmlText)
			{
				case "enchantShieldOrSigil1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantShieldOrSigil10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				case "enchantShieldOrSigil50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredArmor, itemRequiredArmorCount);
					break;
				
			}
		}
		// Jewels
		else if (event.equals("enchantUpperEarring1") || event.equals("enchantUpperEarring10") || event.equals("enchantUpperEarring50"))
		{
			armorType = Inventory.PAPERDOLL_LEAR;
			enchantType = "EnchantJewels.htm";
			
			switch (htmlText)
			{
				case "enchantUpperEarring1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantUpperEarring10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantUpperEarring50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				
			}
		}
		else if (event.equals("enchantLowerEarring1") || event.equals("enchantLowerEarring10") || event.equals("enchantLowerEarring50"))
		{
			armorType = Inventory.PAPERDOLL_REAR;
			enchantType = "EnchantJewels.htm";
			
			switch (htmlText)
			{
				case "enchantLowerEarring1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantLowerEarring10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantLowerEarring50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				
			}
		}
		else if (event.equals("enchantNecklace1") || event.equals("enchantNecklace10") || event.equals("enchantNecklace50"))
		{
			armorType = Inventory.PAPERDOLL_NECK;
			enchantType = "EnchantJewels.htm";
			
			switch (htmlText)
			{
				case "enchantNecklace1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantNecklace10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantNecklace50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				
			}
		}
		else if (event.equals("enchantUpperRing1") || event.equals("enchantUpperRing10") || event.equals("enchantUpperRing50"))
		{
			armorType = Inventory.PAPERDOLL_LFINGER;
			enchantType = "EnchantJewels.htm";
			
			switch (htmlText)
			{
				case "enchantUpperRing1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantUpperRing10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantUpperRing50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
			}
		}
		else if (event.equals("enchantLowerRing1") || event.equals("enchantLowerRing10") || event.equals("enchantLowerRing50"))
		{
			armorType = Inventory.PAPERDOLL_RFINGER;
			enchantType = "EnchantJewels.htm";
			
			switch (htmlText)
			{
				case "enchantLowerRing1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantLowerRing10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
				case "enchantLowerRing50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredJewels, itemRequiredJewelsCount);
					break;
			}
		}
		// Belt/Shirt
		else if (event.equals("enchantBelt1") || event.equals("enchantBelt10") || event.equals("enchantBelt50"))
		{
			armorType = Inventory.PAPERDOLL_BELT;
			enchantType = "EnchantBeltShirt.htm";
			
			switch (htmlText)
			{
				case "enchantBelt1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredBeltShirt, itemRequiredBeltShirtCount);
					break;
				case "enchantBelt10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredBeltShirt, itemRequiredBeltShirtCount);
					break;
				case "enchantBelt50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredBeltShirt, itemRequiredBeltShirtCount);
					break;
			}
		}
		else if (event.equals("enchantShirt1") || event.equals("enchantShirt10") || event.equals("enchantShirt50"))
		{
			armorType = Inventory.PAPERDOLL_UNDER;
			enchantType = "EnchantBeltShirt.htm";
			
			switch (htmlText)
			{
				case "enchantShirt1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredBeltShirt, itemRequiredBeltShirtCount);
					break;
				case "enchantShirt10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredBeltShirt, itemRequiredBeltShirtCount);
					break;
				case "enchantShirt50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredBeltShirt, itemRequiredBeltShirtCount);
					break;
			}
		}
		// Weapon
		else if (event.equals("enchantWeapon1") || event.equals("enchantWeapon10") || event.equals("enchantWeapon50"))
		{
			armorType = Inventory.PAPERDOLL_RHAND;
			enchantType = "EnchantWeapon.htm";
			
			switch (htmlText)
			{
				case "enchantWeapon1":
					htmlText = enchant(enchantType, player, armorType, itemRequiredWeapon, itemRequiredWeaponCount);
					break;
				case "enchantWeapon10":
					htmlText = enchant10(enchantType, player, armorType, itemRequiredWeapon, itemRequiredWeaponCount);
					break;
				case "enchantWeapon50":
					htmlText = enchant50(enchantType, player, armorType, itemRequiredWeapon, itemRequiredWeaponCount);
					break;
				
			}
			
		}
		
		return htmlText;
	}
	
	private String enchant(String enchantType, L2PcInstance player, int armorType, int itemRequired, int itemRequiredCount)
	{
		QuestState st = player.getQuestState(getName());
		
		int currentEnchant = 0;
		int newEnchantLevel = 0;
		
		if (st.getQuestItemsCount(itemRequired) >= itemRequiredCount)
		{
			try
			{
				L2ItemInstance item = getItemToEnchant(player, armorType);
				
				if (item != null)
				{
					currentEnchant = item.getEnchantLevel();
					
					if (currentEnchant < 65535)
					{
						newEnchantLevel = setEnchant(player, item, currentEnchant + 1, armorType);
						
						if (newEnchantLevel > 0)
						{
							st.takeItems(itemRequired, itemRequiredCount);
							player.sendMessage("You successfully enchanted your " + item.getItem().getName() + " from +" + currentEnchant + " to +" + newEnchantLevel + "!");
							
							String htmlContent = "<center>You successfully enchanted your:<br>" + "<font color=\"FF7200\">" + item.getItem().getName() + "</font><br>" + "From: <font color=\"AEFF00\">+" + currentEnchant + "</font> to <font color=\"AEFF00\">+" + newEnchantLevel + "</font>" + "</center>";
							
							return drawHtml("Congratulations!", htmlContent, enchantType);
						}
					}
					else
					{
						player.sendMessage("Your " + item.getItem().getName() + " is already +65535!");
						return drawHtml("It's already +65535", "<center>Your <font color=\"FF7200\">" + item.getItem().getName() + "</font> is already +65535!</center>", enchantType);
					}
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
				player.sendMessage("Something went wrong. Are equiped with the item?");
				return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
			}
			catch (NumberFormatException e)
			{
				player.sendMessage("Something went wrong. Are equiped with the item?");
				return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
			}
			
			player.sendMessage("Something went wrong. Are equiped with the item?");
			return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
		}
		else
		{
			String content = "<center>" + "Not enough <font color=\"FF7200\">Golden Coin</font>!<br>";
			
			if (st.getQuestItemsCount(itemRequired) > 0)
			{
				content += "You have " + st.getQuestItemsCount(itemRequired) + " Golden Coin,<br1>" + "Need " + (itemRequiredCount - st.getQuestItemsCount(itemRequired)) + " more.";
			}
			else
			{
				content += "You need <font color=\"FF7200\">" + itemRequiredCount + " Golden Coin</font>!";
			}
			
			content += "</center>";
			
			return drawHtml("Not Enough Items", content, enchantType);
		}
	}
	
	private String enchant10(String enchantType, L2PcInstance player, int armorType, int itemRequired, int itemRequiredCount)
	{
		QuestState st = player.getQuestState(getName());
		
		int currentEnchant = 0;
		int newEnchantLevel = 0;
		
		if (st.getQuestItemsCount(itemRequired) >= (itemRequiredCount * 10))
		{
			try
			{
				L2ItemInstance item = getItemToEnchant(player, armorType);
				
				if (item != null)
				{
					currentEnchant = item.getEnchantLevel();
					
					if (currentEnchant < 65535)
					{
						newEnchantLevel = setEnchant(player, item, currentEnchant + 10, armorType);
						
						if (newEnchantLevel > 0)
						{
							st.takeItems(itemRequired, itemRequiredCount * 10);
							player.sendMessage("You successfully enchanted your " + item.getItem().getName() + " from +" + currentEnchant + " to +" + newEnchantLevel + "!");
							
							String htmlContent = "<center>You successfully enchanted your:<br>" + "<font color=\"FF7200\">" + item.getItem().getName() + "</font><br>" + "From: <font color=\"AEFF00\">+" + currentEnchant + "</font> to <font color=\"AEFF00\">+" + newEnchantLevel + "</font>" + "</center>";
							
							return drawHtml("Congratulations!", htmlContent, enchantType);
						}
					}
					else
					{
						player.sendMessage("Your " + item.getItem().getName() + " is already +65535!");
						return drawHtml("It's already +65535", "<center>Your <font color=\"FF7200\">" + item.getItem().getName() + "</font> is already +65535!</center>", enchantType);
					}
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
				player.sendMessage("Something went wrong. Are equiped with the item?");
				return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
			}
			catch (NumberFormatException e)
			{
				player.sendMessage("Something went wrong. Are equiped with the item?");
				return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
			}
			
			player.sendMessage("Something went wrong. Are equiped with the item?");
			return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
		}
		else
		{
			String content = "<center>" + "Not enough <font color=\"FF7200\">Golden Coin</font>!<br>";
			
			if (st.getQuestItemsCount(itemRequired) > 0)
			{
				content += "You have " + st.getQuestItemsCount(itemRequired) + " Golden Coin,<br1>" + "Need " + (itemRequiredCount*10 - st.getQuestItemsCount(itemRequired)) + " more.";
			}
			else
			{
				content += "You need <font color=\"FF7200\">" + itemRequiredCount + " Golden Coin</font>!";
			}
			
			content += "</center>";
			
			return drawHtml("Not Enough Items", content, enchantType);
		}
	}
	
	private String enchant50(String enchantType, L2PcInstance player, int armorType, int itemRequired, int itemRequiredCount)
	{
		QuestState st = player.getQuestState(getName());
		
		int currentEnchant = 0;
		int newEnchantLevel = 0;
		
		if (st.getQuestItemsCount(itemRequired) >= (itemRequiredCount * 50))
		{
			try
			{
				L2ItemInstance item = getItemToEnchant(player, armorType);
				
				if (item != null)
				{
					currentEnchant = item.getEnchantLevel();
					
					if (currentEnchant < 65535)
					{
						newEnchantLevel = setEnchant(player, item, currentEnchant + 50, armorType);
						
						if (newEnchantLevel > 0)
						{
							st.takeItems(itemRequired, itemRequiredCount * 50);
							player.sendMessage("You successfully enchanted your " + item.getItem().getName() + " from +" + currentEnchant + " to +" + newEnchantLevel + "!");
							
							String htmlContent = "<center>You successfully enchanted your:<br>" + "<font color=\"FF7200\">" + item.getItem().getName() + "</font><br>" + "From: <font color=\"AEFF00\">+" + currentEnchant + "</font> to <font color=\"AEFF00\">+" + newEnchantLevel + "</font>" + "</center>";
							
							return drawHtml("Congratulations!", htmlContent, enchantType);
						}
					}
					else
					{
						player.sendMessage("Your " + item.getItem().getName() + " is already +65535!");
						return drawHtml("It's already +65535", "<center>Your <font color=\"FF7200\">" + item.getItem().getName() + "</font> is already +65535!</center>", enchantType);
					}
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
				player.sendMessage("Something went wrong. Are equiped with the item?");
				return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
			}
			catch (NumberFormatException e)
			{
				player.sendMessage("Something went wrong. Are equiped with the item?");
				return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
			}
			
			player.sendMessage("Something went wrong. Are equiped with the item?");
			return drawHtml("Error Enchant", "<center>Something went wrong.<br>Are equiped with the item?</center>", enchantType);
		}
		else
		{
			String content = "<center>" + "Not enough <font color=\"FF7200\">Golden Coin</font>!<br>";
			
			if (st.getQuestItemsCount(itemRequired) > 0)
			{
				content += "You have " + st.getQuestItemsCount(itemRequired) + " Golden Coin,<br1>" + "Need " + (itemRequiredCount*50 - st.getQuestItemsCount(itemRequired)) + " more.";
			}
			else
			{
				content += "You need <font color=\"FF7200\">" + itemRequiredCount + " Golden Coin</font>!";
			}
			
			content += "</center>";
			
			return drawHtml("Not Enough Items", content, enchantType);
		}
	}
	
	private L2ItemInstance getItemToEnchant(L2PcInstance player, int armorType)
	{
		L2ItemInstance itemInstance = null;
		L2ItemInstance parmorInstance = player.getInventory().getPaperdollItem(armorType);
		
		if ((parmorInstance != null) && (parmorInstance.getLocationSlot() == armorType))
		{
			itemInstance = parmorInstance;
			
			if (itemInstance != null)
			{
				return itemInstance;
			}
		}
		
		return null;
	}
	
	private int setEnchant(L2PcInstance player, L2ItemInstance item, int newEnchantLevel, int armorType)
	{
		if (item != null)
		{
			// set enchant value
			player.getInventory().unEquipItemInSlot(armorType);
			item.setEnchantLevel(newEnchantLevel);
			player.getInventory().equipItem(item);
			
			// send packets
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(item);
			player.sendPacket(iu);
			player.broadcastPacket(new CharInfo(player));
			player.sendPacket(new UserInfo(player));
			player.broadcastPacket(new ExBrExtraUserInfo(player));
			
			return newEnchantLevel;
		}
		
		return -1;
	}
	
	public String drawHtml(String title, String content, String enchantType)
	{
		String html = "<html>" + "<title>Enchant Manager</title>" + "<body>" + "<center><img src=\"L2UI_CH3.herotower_deco\" width=256 height=32><br>" + "<font color=\"FF9900\">" + title + "</font></center><br>" + content + "<br><br>" + "<center><a action=\"bypass -h Quest Enchant " + enchantType + "\">Go Back</a></center>" + "</body>" + "</html>";
		
		return html;
	}
}
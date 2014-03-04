/**
 *  Catroid: An on-device visual programming system for Android devices
 *  Copyright (C) 2010-2013 The Catrobat Team
 *  (<http://developer.catrobat.org/credits>)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *  
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://developer.catrobat.org/license_additional_term
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Affero General Public License for more details.
 *  
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.catroid.standalone.tictactoe_master.ui.dialogs;

import android.content.Intent;

import org.catrobat.catroid.standalone.tictactoe_master.ProjectManager;
import org.catrobat.catroid.standalone.tictactoe_master.content.Sprite;
import org.catrobat.catroid.standalone.tictactoe_master.R;
import org.catrobat.catroid.standalone.tictactoe_master.ui.ScriptActivity;
import org.catrobat.catroid.standalone.tictactoe_master.utils.Utils;

public class NewSpriteDialog extends TextDialog {

	public static final String DIALOG_FRAGMENT_TAG = "dialog_new_sprite";

	@Override
	protected void initialize() {
		inputTitle.setText(R.string.sprite_name);
	}

	@Override
	protected boolean handleOkButton() {
		String newSpriteName = input.getText().toString().trim();
		ProjectManager projectManager = ProjectManager.getInstance();

		if (projectManager.spriteExists(newSpriteName)) {
			Utils.showErrorDialog(getActivity(), R.string.spritename_already_exists);
			return false;
		}

		if (newSpriteName == null || newSpriteName.equalsIgnoreCase("")) {
			Utils.showErrorDialog(getActivity(), R.string.spritename_invalid);
			return false;
		}

		if (projectManager.spriteExists(newSpriteName)) {
			Utils.showErrorDialog(getActivity(), R.string.spritename_already_exists);
			return false;
		}

		Sprite sprite = new Sprite(newSpriteName);
		projectManager.addSprite(sprite);

		getActivity().sendBroadcast(new Intent(ScriptActivity.ACTION_SPRITES_LIST_CHANGED));

		return true;
	}

	@Override
	protected String getTitle() {
		return getString(R.string.new_sprite_dialog_title);
	}

	@Override
	protected String getHint() {
		return getString(R.string.new_sprite_dialog_default_sprite_name);
	}
}
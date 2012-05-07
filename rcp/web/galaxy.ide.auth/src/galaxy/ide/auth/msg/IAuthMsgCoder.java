/*******************************************************************************
 * Copyright(C) 2011 Arzan Corporation. All rights reserved.
 *
 * Contributors:
 *     Arzan Corporation - initial API and implementation
 *******************************************************************************/
package galaxy.ide.auth.msg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author dzh
 * @date Nov 15, 2011 7:14:02 PM
 */
interface IAuthMsgCoder {

	byte[] toWire(AuthMsg msg) throws UnsupportedEncodingException;

	AuthMsg fromWire(byte[] input) throws IOException, MsgCoderException;
}

/*
Copyleft (C) 2005 H�lio Perroni Filho
xperroni@yahoo.com
ICQ: 2490863

This file is part of ChatterBean.

ChatterBean is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.

ChatterBean is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with ChatterBean (look at the Documents/ directory); if not, either write to the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307 USA, or visit (http://www.gnu.org/licenses/gpl.txt).
 */

package bitoflife.chatterbean.config;

import bitoflife.chatterbean.text.Tokenizer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TokenizerConfigStream extends DefaultHandler implements
		TokenizerConfig {
	/*
	 * Attribute Section
	 */

	private static final String[] STRING_ARRAY = new String[0];

	private final SAXParser parser;

	// 这里把splitters列表长度设置成6，那如果splitters.xml中超过6个分割符不就无效了吗？？？
	private final List<String> splitters = new ArrayList<String>(6);

	private boolean ignoreWhitespace;

	/*
	 * Constructor Section
	 */

	public TokenizerConfigStream() throws ConfigException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
		} catch (Exception e) {
			throw new ConfigException(e);
		}
	}

	public TokenizerConfigStream(InputStream input) throws ConfigException {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			parser = factory.newSAXParser();
			parse(input);
		} catch (ConfigException e) {
			throw e;
		} catch (Exception e) {
			throw new ConfigException(e);
		}
	}

	/*
	 * Event Section
	 */

	public void startElement(String namespace, String name, String qname,
			Attributes attributes) throws SAXException {
		if ("splitter".equals(qname))
			splitters.add(attributes.getValue(0)); // 这里只负责把分隔符记录下来，却忽略了分隔符的类型（sentence,word）
	}

	/*
	 * Method Section
	 */

	public Tokenizer newInstance() {
		return new Tokenizer(splitters());
	}

	public void parse(InputStream input) throws ConfigException {
		try {
			splitters.clear();
			ignoreWhitespace = true;
			parser.parse(input, this);
		} catch (Exception e) {
			throw new ConfigException(e);
		}
	}

	/*
	 * Accessor Section
	 */

	public String[] splitters() {
		return splitters.toArray(STRING_ARRAY);
	}
}

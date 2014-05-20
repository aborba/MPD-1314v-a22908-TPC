/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.isel.mpd14.tohtml;

/**
 * @author Miguel Gamboa at CCISEL
 */
interface HtmlNode {

  final int bodyIndentation = 1;
  final String INDENT = "  ";

  default String print() {
    return print(0);
  }

  default String calcIndent(int indent) {
    int multiplier = indent + bodyIndentation + 1;
    StringBuilder sb = new StringBuilder(INDENT.length() * multiplier + 1);
    for (int i = 0; i < multiplier; i++) {
      sb.append(INDENT);
    }

    return sb.toString();
  }

  String print(int indent);

}

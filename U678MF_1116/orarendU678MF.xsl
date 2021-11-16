<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    
    <xsl:template match = "/"> 
        
        <html> 
            <body> 
                <h2>CSL órarend – 2021/22. I. félév.</h2> 
                
                <table border = "1"> 
                    <tr bgcolor = "#729af7"> 
                        <th>targy</th> 
                        <th bgcolor = "#f7f372">idopont</th> 
                        <th>helyszin</th> 
                        <th bgcolor = "#f7f372">oktato</th> 
                        <th>szak</th> 
                        <th bgcolor = "#f7f372">id</th>
                        <th>tipus</th> 
                    </tr> 
                    
                    
                    <xsl:for-each select="orarend/ora"> 
                        <tr> 
                            <td><xsl:value-of select = "targy"/></td> 
                            <td><xsl:value-of select = "idopont"/></td> 
                            <td><xsl:value-of select = "helyszin"/></td> 
                            <td><xsl:value-of select = "oktato"/></td> 
                            <td><xsl:value-of select = "szak"/></td>
                            <td><xsl:value-of select = "@id"/></td> 
                            <td><xsl:value-of select = "@tipus"/></td>  
                        </tr> 
                    </xsl:for-each> 
                    
                </table> 
            </body> 
        </html> 
    </xsl:template>  
</xsl:stylesheet>
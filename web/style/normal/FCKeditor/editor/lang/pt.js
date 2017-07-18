/*
 * FCKeditor - The text editor for Internet - http://www.fckeditor.net
 * Copyright (C) 2003-2008 Frederico Caldeira Knabben
 *
 * == BEGIN LICENSE ==
 *
 * Licensed under the terms of any of the following licenses at your
 * choice:
 *
 *  - GNU General Public License Version 2 or later (the "GPL")
 *    http://www.gnu.org/licenses/gpl.html
 *
 *  - GNU Lesser General Public License Version 2.1 or later (the "LGPL")
 *    http://www.gnu.org/licenses/lgpl.html
 *
 *  - Mozilla Public License Version 1.1 or later (the "MPL")
 *    http://www.mozilla.org/MPL/MPL-1.1.html
 *
 * == END LICENSE ==
 *
 * Portuguese language file.
 */

var FCKLang =
{
// Language direction : "ltr" (left to right) or "rtl" (right to left).
Dir					: "ltr",

ToolbarCollapse		: "Fechar Barra",
ToolbarExpand		: "Expandir Barra",

// Toolbar Items and Context Menu
Save				: "Guardar",
NewPage				: "Nova Página",
Preview				: "Pré-visualizar",
Cut					: "Cortar",
Copy				: "Copiar",
Paste				: "Colar",
PasteText			: "Colar como texto n?o formatado",
PasteWord			: "Colar do Word",
Print				: "Imprimir",
SelectAll			: "Seleccionar Tudo",
RemoveFormat		: "Eliminar Formato",
InsertLinkLbl		: "Hiperliga??o",
InsertLink			: "Inserir/Editar Hiperliga??o",
RemoveLink			: "Eliminar Hiperliga??o",
Anchor				: " Inserir/Editar ?ncora",
AnchorDelete		: "Remove Anchor",	//MISSING
InsertImageLbl		: "Imagem",
InsertImage			: "Inserir/Editar Imagem",
InsertFlashLbl		: "Flash",
InsertFlash			: "Inserir/Editar Flash",
InsertTableLbl		: "Tabela",
InsertTable			: "Inserir/Editar Tabela",
InsertLineLbl		: "Linha",
InsertLine			: "Inserir Linha Horizontal",
InsertSpecialCharLbl: "Caracter Especial",
InsertSpecialChar	: "Inserir Caracter Especial",
InsertSmileyLbl		: "Emoticons",
InsertSmiley		: "Inserir Emoticons",
About				: "Acerca do FCKeditor",
Bold				: "Negrito",
Italic				: "Itálico",
Underline			: "Sublinhado",
StrikeThrough		: "Rasurado",
Subscript			: "Superior à Linha",
Superscript			: "Inferior à Linha",
LeftJustify			: "Alinhar à Esquerda",
CenterJustify		: "Alinhar ao Centro",
RightJustify		: "Alinhar à Direita",
BlockJustify		: "Justificado",
DecreaseIndent		: "Diminuir Avan?o",
IncreaseIndent		: "Aumentar Avan?o",
Blockquote			: "Blockquote",	//MISSING
Undo				: "Anular",
Redo				: "Repetir",
NumberedListLbl		: "Numera??o",
NumberedList		: "Inserir/Eliminar Numera??o",
BulletedListLbl		: "Marcas",
BulletedList		: "Inserir/Eliminar Marcas",
ShowTableBorders	: "Mostrar Limites da Tabelas",
ShowDetails			: "Mostrar Parágrafo",
Style				: "Estilo",
FontFormat			: "Formato",
Font				: "Tipo de Letra",
FontSize			: "Tamanho",
TextColor			: "Cor do Texto",
BGColor				: "Cor de Fundo",
Source				: "Fonte",
Find				: "Procurar",
Replace				: "Substituir",
SpellCheck			: "Verifica??o Ortográfica",
UniversalKeyboard	: "Teclado Universal",
PageBreakLbl		: "Quebra de Página",
PageBreak			: "Inserir Quebra de Página",

Form			: "Formulário",
Checkbox		: "Caixa de Verifica??o",
RadioButton		: "Bot?o de Op??o",
TextField		: "Campo de Texto",
Textarea		: "área de Texto",
HiddenField		: "Campo Escondido",
Button			: "Bot?o",
SelectionField	: "Caixa de Combina??o",
ImageButton		: "Bot?o de Imagem",

FitWindow		: "Maximizar o tamanho do editor",
ShowBlocks		: "Show Blocks",	//MISSING

// Context Menu
EditLink			: "Editar Hiperliga??o",
CellCM				: "Célula",
RowCM				: "Linha",
ColumnCM			: "Coluna",
InsertRowAfter		: "Insert Row After",	//MISSING
InsertRowBefore		: "Insert Row Before",	//MISSING
DeleteRows			: "Eliminar Linhas",
InsertColumnAfter	: "Insert Column After",	//MISSING
InsertColumnBefore	: "Insert Column Before",	//MISSING
DeleteColumns		: "Eliminar Coluna",
InsertCellAfter		: "Insert Cell After",	//MISSING
InsertCellBefore	: "Insert Cell Before",	//MISSING
DeleteCells			: "Eliminar Célula",
MergeCells			: "Unir Células",
MergeRight			: "Merge Right",	//MISSING
MergeDown			: "Merge Down",	//MISSING
HorizontalSplitCell	: "Split Cell Horizontally",	//MISSING
VerticalSplitCell	: "Split Cell Vertically",	//MISSING
TableDelete			: "Eliminar Tabela",
CellProperties		: "Propriedades da Célula",
TableProperties		: "Propriedades da Tabela",
ImageProperties		: "Propriedades da Imagem",
FlashProperties		: "Propriedades do Flash",

AnchorProp			: "Propriedades da ?ncora",
ButtonProp			: "Propriedades do Bot?o",
CheckboxProp		: "Propriedades da Caixa de Verifica??o",
HiddenFieldProp		: "Propriedades do Campo Escondido",
RadioButtonProp		: "Propriedades do Bot?o de Op??o",
ImageButtonProp		: "Propriedades do Bot?o de imagens",
TextFieldProp		: "Propriedades do Campo de Texto",
SelectionFieldProp	: "Propriedades da Caixa de Combina??o",
TextareaProp		: "Propriedades da área de Texto",
FormProp			: "Propriedades do Formulário",

FontFormats			: "Normal;Formatado;Endere?o;Título 1;Título 2;Título 3;Título 4;Título 5;Título 6",

// Alerts and Messages
ProcessingXHTML		: "A Processar XHTML. Por favor, espere...",
Done				: "Concluído",
PasteWordConfirm	: "O texto que deseja parece ter sido copiado do Word. Deseja limpar a formata??o antes de colar?",
NotCompatiblePaste	: "Este comando só está disponível para Internet Explorer vers?o 5.5 ou superior. Deseja colar sem limpar a formata??o?",
UnknownToolbarItem	: "Item de barra desconhecido \"%1\"",
UnknownCommand		: "Nome de comando desconhecido \"%1\"",
NotImplemented		: "Comando n?o implementado",
UnknownToolbarSet	: "Nome de barra \"%1\" n?o definido",
NoActiveX			: "As defini??es de seguran?a do navegador podem limitar algumas potencalidades do editr. Deve activar a op??o \"Executar controlos e extens?es ActiveX\". Pode ocorrer erros ou verificar que faltam potencialidades.",
BrowseServerBlocked : "N?o foi possível abrir o navegador de recursos. Certifique-se que todos os bloqueadores de popup est?o desactivados.",
DialogBlocked		: "N?o foi possível abrir a janela de diálogo. Certifique-se que todos os bloqueadores de popup est?o desactivados.",

// Dialogs
DlgBtnOK			: "OK",
DlgBtnCancel		: "Cancelar",
DlgBtnClose			: "Fechar",
DlgBtnBrowseServer	: "Navegar no Servidor",
DlgAdvancedTag		: "Avan?ado",
DlgOpOther			: "<Outro>",
DlgInfoTab			: "Informa??o",
DlgAlertUrl			: "Por favor introduza o URL",

// General Dialogs Labels
DlgGenNotSet		: "<N?o definido>",
DlgGenId			: "Id",
DlgGenLangDir		: "Orienta??o de idioma",
DlgGenLangDirLtr	: "Esquerda à Direita (LTR)",
DlgGenLangDirRtl	: "Direita a Esquerda (RTL)",
DlgGenLangCode		: "Código de Idioma",
DlgGenAccessKey		: "Chave de Acesso",
DlgGenName			: "Nome",
DlgGenTabIndex		: "índice de Tubula??o",
DlgGenLongDescr		: "Descri??o Completa do URL",
DlgGenClass			: "Classes de Estilo de Folhas Classes",
DlgGenTitle			: "Título",
DlgGenContType		: "Tipo de Conteúdo",
DlgGenLinkCharset	: "Fonte de caracteres vinculado",
DlgGenStyle			: "Estilo",

// Image Dialog
DlgImgTitle			: "Propriedades da Imagem",
DlgImgInfoTab		: "Informa??o da Imagem",
DlgImgBtnUpload		: "Enviar para o Servidor",
DlgImgURL			: "URL",
DlgImgUpload		: "Carregar",
DlgImgAlt			: "Texto Alternativo",
DlgImgWidth			: "Largura",
DlgImgHeight		: "Altura",
DlgImgLockRatio		: "Proporcional",
DlgBtnResetSize		: "Tamanho Original",
DlgImgBorder		: "Limite",
DlgImgHSpace		: "Esp.Horiz",
DlgImgVSpace		: "Esp.Vert",
DlgImgAlign			: "Alinhamento",
DlgImgAlignLeft		: "Esquerda",
DlgImgAlignAbsBottom: "Abs inferior",
DlgImgAlignAbsMiddle: "Abs centro",
DlgImgAlignBaseline	: "Linha de base",
DlgImgAlignBottom	: "Fundo",
DlgImgAlignMiddle	: "Centro",
DlgImgAlignRight	: "Direita",
DlgImgAlignTextTop	: "Topo do texto",
DlgImgAlignTop		: "Topo",
DlgImgPreview		: "Pré-visualizar",
DlgImgAlertUrl		: "Por favor introduza o URL da imagem",
DlgImgLinkTab		: "Hiperliga??o",

// Flash Dialog
DlgFlashTitle		: "Propriedades do Flash",
DlgFlashChkPlay		: "Reproduzir automaticamente",
DlgFlashChkLoop		: "Loop",
DlgFlashChkMenu		: "Permitir Menu do Flash",
DlgFlashScale		: "Escala",
DlgFlashScaleAll	: "Mostrar tudo",
DlgFlashScaleNoBorder	: "Sem Limites",
DlgFlashScaleFit	: "Tamanho Exacto",

// Link Dialog
DlgLnkWindowTitle	: "Hiperliga??o",
DlgLnkInfoTab		: "Informa??o de Hiperliga??o",
DlgLnkTargetTab		: "Destino",

DlgLnkType			: "Tipo de Hiperliga??o",
DlgLnkTypeURL		: "URL",
DlgLnkTypeAnchor	: "Referência a esta página",
DlgLnkTypeEMail		: "E-Mail",
DlgLnkProto			: "Protocolo",
DlgLnkProtoOther	: "<outro>",
DlgLnkURL			: "URL",
DlgLnkAnchorSel		: "Seleccionar una referência",
DlgLnkAnchorByName	: "Por Nome de Referência",
DlgLnkAnchorById	: "Por ID de elemento",
DlgLnkNoAnchors		: "(N?o há referências disponíveis no documento)",
DlgLnkEMail			: "Endere?o de E-Mail",
DlgLnkEMailSubject	: "Título de Mensagem",
DlgLnkEMailBody		: "Corpo da Mensagem",
DlgLnkUpload		: "Carregar",
DlgLnkBtnUpload		: "Enviar ao Servidor",

DlgLnkTarget		: "Destino",
DlgLnkTargetFrame	: "<Frame>",
DlgLnkTargetPopup	: "<Janela de popup>",
DlgLnkTargetBlank	: "Nova Janela(_blank)",
DlgLnkTargetParent	: "Janela Pai (_parent)",
DlgLnkTargetSelf	: "Mesma janela (_self)",
DlgLnkTargetTop		: "Janela primaria (_top)",
DlgLnkTargetFrameName	: "Nome do Frame Destino",
DlgLnkPopWinName	: "Nome da Janela de Popup",
DlgLnkPopWinFeat	: "Características de Janela de Popup",
DlgLnkPopResize		: "Ajustável",
DlgLnkPopLocation	: "Barra de localiza??o",
DlgLnkPopMenu		: "Barra de Menu",
DlgLnkPopScroll		: "Barras de deslocamento",
DlgLnkPopStatus		: "Barra de Estado",
DlgLnkPopToolbar	: "Barra de Ferramentas",
DlgLnkPopFullScrn	: "Janela Completa (IE)",
DlgLnkPopDependent	: "Dependente (Netscape)",
DlgLnkPopWidth		: "Largura",
DlgLnkPopHeight		: "Altura",
DlgLnkPopLeft		: "Posi??o Esquerda",
DlgLnkPopTop		: "Posi??o Direita",

DlnLnkMsgNoUrl		: "Por favor introduza a hiperliga??o URL",
DlnLnkMsgNoEMail	: "Por favor introduza o endere?o de e-mail",
DlnLnkMsgNoAnchor	: "Por favor seleccione uma referência",
DlnLnkMsgInvPopName	: "The popup name must begin with an alphabetic character and must not contain spaces",	//MISSING

// Color Dialog
DlgColorTitle		: "Seleccionar Cor",
DlgColorBtnClear	: "Nenhuma",
DlgColorHighlight	: "Destacado",
DlgColorSelected	: "Seleccionado",

// Smiley Dialog
DlgSmileyTitle		: "Inserir um Emoticon",

// Special Character Dialog
DlgSpecialCharTitle	: "Seleccione um caracter especial",

// Table Dialog
DlgTableTitle		: "Propriedades da Tabela",
DlgTableRows		: "Linhas",
DlgTableColumns		: "Colunas",
DlgTableBorder		: "Tamanho do Limite",
DlgTableAlign		: "Alinhamento",
DlgTableAlignNotSet	: "<N?o definido>",
DlgTableAlignLeft	: "Esquerda",
DlgTableAlignCenter	: "Centrado",
DlgTableAlignRight	: "Direita",
DlgTableWidth		: "Largura",
DlgTableWidthPx		: "pixeis",
DlgTableWidthPc		: "percentagem",
DlgTableHeight		: "Altura",
DlgTableCellSpace	: "Esp. e/células",
DlgTableCellPad		: "Esp. interior",
DlgTableCaption		: "Título",
DlgTableSummary		: "Sumário",

// Table Cell Dialog
DlgCellTitle		: "Propriedades da Célula",
DlgCellWidth		: "Largura",
DlgCellWidthPx		: "pixeis",
DlgCellWidthPc		: "percentagem",
DlgCellHeight		: "Altura",
DlgCellWordWrap		: "Moldar Texto",
DlgCellWordWrapNotSet	: "<N?o definido>",
DlgCellWordWrapYes	: "Sim",
DlgCellWordWrapNo	: "N?o",
DlgCellHorAlign		: "Alinhamento Horizontal",
DlgCellHorAlignNotSet	: "<N?o definido>",
DlgCellHorAlignLeft	: "Esquerda",
DlgCellHorAlignCenter	: "Centrado",
DlgCellHorAlignRight: "Direita",
DlgCellVerAlign		: "Alinhamento Vertical",
DlgCellVerAlignNotSet	: "<N?o definido>",
DlgCellVerAlignTop	: "Topo",
DlgCellVerAlignMiddle	: "Médio",
DlgCellVerAlignBottom	: "Fundi",
DlgCellVerAlignBaseline	: "Linha de Base",
DlgCellRowSpan		: "Unir Linhas",
DlgCellCollSpan		: "Unir Colunas",
DlgCellBackColor	: "Cor do Fundo",
DlgCellBorderColor	: "Cor do Limite",
DlgCellBtnSelect	: "Seleccione...",

// Find and Replace Dialog
DlgFindAndReplaceTitle	: "Find and Replace",	//MISSING

// Find Dialog
DlgFindTitle		: "Procurar",
DlgFindFindBtn		: "Procurar",
DlgFindNotFoundMsg	: "O texto especificado n?o foi encontrado.",

// Replace Dialog
DlgReplaceTitle			: "Substituir",
DlgReplaceFindLbl		: "Texto a Procurar:",
DlgReplaceReplaceLbl	: "Substituir por:",
DlgReplaceCaseChk		: "Maiúsculas/Minúsculas",
DlgReplaceReplaceBtn	: "Substituir",
DlgReplaceReplAllBtn	: "Substituir Tudo",
DlgReplaceWordChk		: "Coincidir com toda a palavra",

// Paste Operations / Dialog
PasteErrorCut	: "A configura??o de seguran?a do navegador n?o permite a execu??o automática de opera??es de cortar. Por favor use o teclado (Ctrl+X).",
PasteErrorCopy	: "A configura??o de seguran?a do navegador n?o permite a execu??o automática de opera??es de copiar. Por favor use o teclado (Ctrl+C).",

PasteAsText		: "Colar como Texto Simples",
PasteFromWord	: "Colar do Word",

DlgPasteMsg2	: "Por favor, cole dentro da seguinte caixa usando o teclado (<STRONG>Ctrl+V</STRONG>) e prima <STRONG>OK</STRONG>.",
DlgPasteSec		: "Because of your browser security settings, the editor is not able to access your clipboard data directly. You are required to paste it again in this window.",	//MISSING
DlgPasteIgnoreFont		: "Ignorar da defini??es do Tipo de Letra ",
DlgPasteRemoveStyles	: "Remover as defini??es de Estilos",

// Color Picker
ColorAutomatic	: "Automático",
ColorMoreColors	: "Mais Cores...",

// Document Properties
DocProps		: "Propriedades do Documento",

// Anchor Dialog
DlgAnchorTitle		: "Propriedades da ?ncora",
DlgAnchorName		: "Nome da ?ncora",
DlgAnchorErrorName	: "Por favor, introduza o nome da ancora",

// Speller Pages Dialog
DlgSpellNotInDic		: "N?o está num directório",
DlgSpellChangeTo		: "Mudar para",
DlgSpellBtnIgnore		: "Ignorar",
DlgSpellBtnIgnoreAll	: "Ignorar Tudo",
DlgSpellBtnReplace		: "Substituir",
DlgSpellBtnReplaceAll	: "Substituir Tudo",
DlgSpellBtnUndo			: "Anular",
DlgSpellNoSuggestions	: "- Sem sugest?es -",
DlgSpellProgress		: "Verifica??o ortográfica em progresso…",
DlgSpellNoMispell		: "Verifica??o ortográfica completa: n?o foram encontrados erros",
DlgSpellNoChanges		: "Verifica??o ortográfica completa: n?o houve altera??o de palavras",
DlgSpellOneChange		: "Verifica??o ortográfica completa: uma palavra alterada",
DlgSpellManyChanges		: "Verifica??o ortográfica completa: %1 palavras alteradas",

IeSpellDownload			: " Verifica??o ortográfica n?o instalada. Quer descarregar agora?",

// Button Dialog
DlgButtonText		: "Texto (Valor)",
DlgButtonType		: "Tipo",
DlgButtonTypeBtn	: "Button",	//MISSING
DlgButtonTypeSbm	: "Submit",	//MISSING
DlgButtonTypeRst	: "Reset",	//MISSING

// Checkbox and Radio Button Dialogs
DlgCheckboxName		: "Nome",
DlgCheckboxValue	: "Valor",
DlgCheckboxSelected	: "Seleccionado",

// Form Dialog
DlgFormName		: "Nome",
DlgFormAction	: "Ac??o",
DlgFormMethod	: "Método",

// Select Field Dialog
DlgSelectName		: "Nome",
DlgSelectValue		: "Valor",
DlgSelectSize		: "Tamanho",
DlgSelectLines		: "linhas",
DlgSelectChkMulti	: "Permitir selec??es múltiplas",
DlgSelectOpAvail	: "Op??es Possíveis",
DlgSelectOpText		: "Texto",
DlgSelectOpValue	: "Valor",
DlgSelectBtnAdd		: "Adicionar",
DlgSelectBtnModify	: "Modificar",
DlgSelectBtnUp		: "Para cima",
DlgSelectBtnDown	: "Para baixo",
DlgSelectBtnSetValue : "Definir um valor por defeito",
DlgSelectBtnDelete	: "Apagar",

// Textarea Dialog
DlgTextareaName	: "Nome",
DlgTextareaCols	: "Colunas",
DlgTextareaRows	: "Linhas",

// Text Field Dialog
DlgTextName			: "Nome",
DlgTextValue		: "Valor",
DlgTextCharWidth	: "Tamanho do caracter",
DlgTextMaxChars		: "Nr. Máximo de Caracteres",
DlgTextType			: "Tipo",
DlgTextTypeText		: "Texto",
DlgTextTypePass		: "Palavra-chave",

// Hidden Field Dialog
DlgHiddenName	: "Nome",
DlgHiddenValue	: "Valor",

// Bulleted List Dialog
BulletedListProp	: "Propriedades da Marca",
NumberedListProp	: "Propriedades da Numera??o",
DlgLstStart			: "Start",	//MISSING
DlgLstType			: "Tipo",
DlgLstTypeCircle	: "Circulo",
DlgLstTypeDisc		: "Disco",
DlgLstTypeSquare	: "Quadrado",
DlgLstTypeNumbers	: "Números (1, 2, 3)",
DlgLstTypeLCase		: "Letras Minúsculas (a, b, c)",
DlgLstTypeUCase		: "Letras Maiúsculas (A, B, C)",
DlgLstTypeSRoman	: "Numera??o Romana em Minúsculas (i, ii, iii)",
DlgLstTypeLRoman	: "Numera??o Romana em Maiúsculas (I, II, III)",

// Document Properties Dialog
DlgDocGeneralTab	: "Geral",
DlgDocBackTab		: "Fundo",
DlgDocColorsTab		: "Cores e Margens",
DlgDocMetaTab		: "Meta Data",

DlgDocPageTitle		: "Título da Página",
DlgDocLangDir		: "Orienta??o de idioma",
DlgDocLangDirLTR	: "Esquerda à Direita (LTR)",
DlgDocLangDirRTL	: "Direita à Esquerda (RTL)",
DlgDocLangCode		: "Código de Idioma",
DlgDocCharSet		: "Codifica??o de Caracteres",
DlgDocCharSetCE		: "Central European",	//MISSING
DlgDocCharSetCT		: "Chinese Traditional (Big5)",	//MISSING
DlgDocCharSetCR		: "Cyrillic",	//MISSING
DlgDocCharSetGR		: "Greek",	//MISSING
DlgDocCharSetJP		: "Japanese",	//MISSING
DlgDocCharSetKR		: "Korean",	//MISSING
DlgDocCharSetTR		: "Turkish",	//MISSING
DlgDocCharSetUN		: "Unicode (UTF-8)",	//MISSING
DlgDocCharSetWE		: "Western European",	//MISSING
DlgDocCharSetOther	: "Outra Codifica??o de Caracteres",

DlgDocDocType		: "Tipo de Cabe?alho do Documento",
DlgDocDocTypeOther	: "Outro Tipo de Cabe?alho do Documento",
DlgDocIncXHTML		: "Incluir Declara??es XHTML",
DlgDocBgColor		: "Cor de Fundo",
DlgDocBgImage		: "Caminho para a Imagem de Fundo",
DlgDocBgNoScroll	: "Fundo Fixo",
DlgDocCText			: "Texto",
DlgDocCLink			: "Hiperliga??o",
DlgDocCVisited		: "Hiperliga??o Visitada",
DlgDocCActive		: "Hiperliga??o Activa",
DlgDocMargins		: "Margem das Páginas",
DlgDocMaTop			: "Topo",
DlgDocMaLeft		: "Esquerda",
DlgDocMaRight		: "Direita",
DlgDocMaBottom		: "Fundo",
DlgDocMeIndex		: "Palavras de Indexa??o do Documento (separadas por virgula)",
DlgDocMeDescr		: "Descri??o do Documento",
DlgDocMeAuthor		: "Autor",
DlgDocMeCopy		: "Direitos de Autor",
DlgDocPreview		: "Pré-visualizar",

// Templates Dialog
Templates			: "Modelos",
DlgTemplatesTitle	: "Modelo de Conteúdo",
DlgTemplatesSelMsg	: "Por favor, seleccione o modelo a abrir no editor<br>(o conteúdo actual será perdido):",
DlgTemplatesLoading	: "A carregar a lista de modelos. Aguarde por favor...",
DlgTemplatesNoTpl	: "(Sem modelos definidos)",
DlgTemplatesReplace	: "Replace actual contents",	//MISSING

// About Dialog
DlgAboutAboutTab	: "Acerca",
DlgAboutBrowserInfoTab	: "Informa??o do Nevegador",
DlgAboutLicenseTab	: "Licen?a",
DlgAboutVersion		: "vers?o",
DlgAboutInfo		: "Para mais informa??es por favor dirija-se a"
};

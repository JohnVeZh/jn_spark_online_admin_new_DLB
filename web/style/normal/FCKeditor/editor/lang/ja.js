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
 * Japanese language file.
 */

var FCKLang =
{
// Language direction : "ltr" (left to right) or "rtl" (right to left).
Dir					: "ltr",

ToolbarCollapse		: "ツールバーを隠す",
ToolbarExpand		: "ツールバーを表示",

// Toolbar Items and Context Menu
Save				: "保存",
NewPage				: "新しいページ",
Preview				: "プレビュー",
Cut					: "切り取り",
Copy				: "コピー",
Paste				: "贴り付け",
PasteText			: "プレーンテキスト贴り付け",
PasteWord			: "ワード文章から贴り付け",
Print				: "印刷",
SelectAll			: "すべて选択",
RemoveFormat		: "フォーマット削除",
InsertLinkLbl		: "リンク",
InsertLink			: "リンク挿入/编集",
RemoveLink			: "リンク削除",
Anchor				: "アンカー挿入/编集",
AnchorDelete		: "アンカー削除",
InsertImageLbl		: "イメージ",
InsertImage			: "イメージ挿入/编集",
InsertFlashLbl		: "Flash",
InsertFlash			: "Flash挿入/编集",
InsertTableLbl		: "テーブル",
InsertTable			: "テーブル挿入/编集",
InsertLineLbl		: "ライン",
InsertLine			: "横罫线",
InsertSpecialCharLbl: "特殊文字",
InsertSpecialChar	: "特殊文字挿入",
InsertSmileyLbl		: "絵文字",
InsertSmiley		: "絵文字挿入",
About				: "FCKeditorヘルプ",
Bold				: "太字",
Italic				: "斜体",
Underline			: "下线",
StrikeThrough		: "打ち消し线",
Subscript			: "添え字",
Superscript			: "上付き文字",
LeftJustify			: "左揃え",
CenterJustify		: "中央揃え",
RightJustify		: "右揃え",
BlockJustify		: "両端揃え",
DecreaseIndent		: "インデント解除",
IncreaseIndent		: "インデント",
Blockquote			: "ブロック引用",
Undo				: "元に戻す",
Redo				: "やり直し",
NumberedListLbl		: "段落番号",
NumberedList		: "段落番号の追加/削除",
BulletedListLbl		: "个条书き",
BulletedList		: "个条书きの追加/削除",
ShowTableBorders	: "テーブルボーダー表示",
ShowDetails			: "详细表示",
Style				: "スタイル",
FontFormat			: "フォーマット",
Font				: "フォント",
FontSize			: "サイズ",
TextColor			: "テキスト色",
BGColor				: "背景色",
Source				: "ソース",
Find				: "検索",
Replace				: "置き换え",
SpellCheck			: "スペルチェック",
UniversalKeyboard	: "ユニバーサル?キーボード",
PageBreakLbl		: "改ページ",
PageBreak			: "改ページ挿入",

Form			: "フォーム",
Checkbox		: "チェックボックス",
RadioButton		: "ラジオボタン",
TextField		: "１行テキスト",
Textarea		: "テキストエリア",
HiddenField		: "不可视フィールド",
Button			: "ボタン",
SelectionField	: "选択フィールド",
ImageButton		: "画像ボタン",

FitWindow		: "エディタサイズを最大にします",
ShowBlocks		: "ブロック表示",

// Context Menu
EditLink			: "リンク编集",
CellCM				: "セル",
RowCM				: "行",
ColumnCM			: "カラム",
InsertRowAfter		: "列の後に挿入",
InsertRowBefore		: "列の前に挿入",
DeleteRows			: "行削除",
InsertColumnAfter	: "カラムの後に挿入",
InsertColumnBefore	: "カラムの前に挿入",
DeleteColumns		: "列削除",
InsertCellAfter		: "セルの後に挿入",
InsertCellBefore	: "セルの前に挿入",
DeleteCells			: "セル削除",
MergeCells			: "セル结合",
MergeRight			: "右に结合",
MergeDown			: "下に结合",
HorizontalSplitCell	: "セルを水平方向分割",
VerticalSplitCell	: "セルを垂直方向に分割",
TableDelete			: "テーブル削除",
CellProperties		: "セル プロパティ",
TableProperties		: "テーブル プロパティ",
ImageProperties		: "イメージ プロパティ",
FlashProperties		: "Flash プロパティ",

AnchorProp			: "アンカー プロパティ",
ButtonProp			: "ボタン プロパティ",
CheckboxProp		: "チェックボックス プロパティ",
HiddenFieldProp		: "不可视フィールド プロパティ",
RadioButtonProp		: "ラジオボタン プロパティ",
ImageButtonProp		: "画像ボタン プロパティ",
TextFieldProp		: "１行テキスト プロパティ",
SelectionFieldProp	: "选択フィールド プロパティ",
TextareaProp		: "テキストエリア プロパティ",
FormProp			: "フォーム プロパティ",

FontFormats			: "标准;书式付き;アドレス;见出し 1;见出し 2;见出し 3;见出し 4;见出し 5;见出し 6;标准 (DIV)",

// Alerts and Messages
ProcessingXHTML		: "XHTML処理中. しばらくお待ちください...",
Done				: "完了",
PasteWordConfirm	: "贴り付けを行うテキストは、ワード文章からコピーされようとしています。贴り付ける前にクリーニングを行いますか？",
NotCompatiblePaste	: "このコマンドはインターネット?エクスプローラーバージョン5.5以上で利用可能です。クリーニングしないで贴り付けを行いますか？",
UnknownToolbarItem	: "未知のツールバー项目 \"%1\"",
UnknownCommand		: "未知のコマンド名 \"%1\"",
NotImplemented		: "コマンドはインプリメントされませんでした。",
UnknownToolbarSet	: "ツールバー设定 \"%1\" 存在しません。",
NoActiveX			: "エラー、警告メッセージなどが発生した场合、ブラウザーのセキュリティ设定によりエディタのいくつかの机能が制限されている可能性があります。セキュリティ设定のオプションで\"ActiveXコントロールとプラグインの実行\"を有効にするにしてください。",
BrowseServerBlocked : "サーバーブラウザーを开くことができませんでした。ポップアップ?ブロック机能が无効になっているか确认してください。",
DialogBlocked		: "ダイアログウィンドウを开くことができませんでした。ポップアップ?ブロック机能が无効になっているか确认してください。",

// Dialogs
DlgBtnOK			: "OK",
DlgBtnCancel		: "キャンセル",
DlgBtnClose			: "闭じる",
DlgBtnBrowseServer	: "サーバーブラウザー",
DlgAdvancedTag		: "高度な设定",
DlgOpOther			: "<その他>",
DlgInfoTab			: "情报",
DlgAlertUrl			: "URLを挿入してください",

// General Dialogs Labels
DlgGenNotSet		: "<なし>",
DlgGenId			: "Id",
DlgGenLangDir		: "文字表记の方向",
DlgGenLangDirLtr	: "左から右 (LTR)",
DlgGenLangDirRtl	: "右から左 (RTL)",
DlgGenLangCode		: "言语コード",
DlgGenAccessKey		: "アクセスキー",
DlgGenName			: "Name属性",
DlgGenTabIndex		: "タブインデックス",
DlgGenLongDescr		: "longdesc属性(长文説明)",
DlgGenClass			: "スタイルシートクラス",
DlgGenTitle			: "Title属性",
DlgGenContType		: "Content Type属性",
DlgGenLinkCharset	: "リンクcharset属性",
DlgGenStyle			: "スタイルシート",

// Image Dialog
DlgImgTitle			: "イメージ プロパティ",
DlgImgInfoTab		: "イメージ 情报",
DlgImgBtnUpload		: "サーバーに送信",
DlgImgURL			: "URL",
DlgImgUpload		: "アップロード",
DlgImgAlt			: "代替テキスト",
DlgImgWidth			: "幅",
DlgImgHeight		: "高さ",
DlgImgLockRatio		: "ロック比率",
DlgBtnResetSize		: "サイズリセット",
DlgImgBorder		: "ボーダー",
DlgImgHSpace		: "横间隔",
DlgImgVSpace		: "縦间隔",
DlgImgAlign			: "行揃え",
DlgImgAlignLeft		: "左",
DlgImgAlignAbsBottom: "下部(絶対的)",
DlgImgAlignAbsMiddle: "中央(絶対的)",
DlgImgAlignBaseline	: "ベースライン",
DlgImgAlignBottom	: "下",
DlgImgAlignMiddle	: "中央",
DlgImgAlignRight	: "右",
DlgImgAlignTextTop	: "テキスト上部",
DlgImgAlignTop		: "上",
DlgImgPreview		: "プレビュー",
DlgImgAlertUrl		: "イメージのURLを入力してください。",
DlgImgLinkTab		: "リンク",

// Flash Dialog
DlgFlashTitle		: "Flash プロパティ",
DlgFlashChkPlay		: "再生",
DlgFlashChkLoop		: "ループ再生",
DlgFlashChkMenu		: "Flashメニュー可能",
DlgFlashScale		: "拡大缩小设定",
DlgFlashScaleAll	: "すべて表示",
DlgFlashScaleNoBorder	: "外が见えない様に拡大",
DlgFlashScaleFit	: "上下左右にフィット",

// Link Dialog
DlgLnkWindowTitle	: "ハイパーリンク",
DlgLnkInfoTab		: "ハイパーリンク 情报",
DlgLnkTargetTab		: "ターゲット",

DlgLnkType			: "リンクタイプ",
DlgLnkTypeURL		: "URL",
DlgLnkTypeAnchor	: "このページのアンカー",
DlgLnkTypeEMail		: "E-Mail",
DlgLnkProto			: "プロトコル",
DlgLnkProtoOther	: "<その他>",
DlgLnkURL			: "URL",
DlgLnkAnchorSel		: "アンカーを选択",
DlgLnkAnchorByName	: "アンカー名",
DlgLnkAnchorById	: "エレメントID",
DlgLnkNoAnchors		: "(ドキュメントにおいて利用可能なアンカーはありません。)",
DlgLnkEMail			: "E-Mail アドレス",
DlgLnkEMailSubject	: "件名",
DlgLnkEMailBody		: "本文",
DlgLnkUpload		: "アップロード",
DlgLnkBtnUpload		: "サーバーに送信",

DlgLnkTarget		: "ターゲット",
DlgLnkTargetFrame	: "<フレーム>",
DlgLnkTargetPopup	: "<ポップアップウィンドウ>",
DlgLnkTargetBlank	: "新しいウィンドウ (_blank)",
DlgLnkTargetParent	: "亲ウィンドウ (_parent)",
DlgLnkTargetSelf	: "同じウィンドウ (_self)",
DlgLnkTargetTop		: "最上位ウィンドウ (_top)",
DlgLnkTargetFrameName	: "目的のフレーム名",
DlgLnkPopWinName	: "ポップアップウィンドウ名",
DlgLnkPopWinFeat	: "ポップアップウィンドウ特徴",
DlgLnkPopResize		: "リサイズ可能",
DlgLnkPopLocation	: "ロケーションバー",
DlgLnkPopMenu		: "メニューバー",
DlgLnkPopScroll		: "スクロールバー",
DlgLnkPopStatus		: "ステータスバー",
DlgLnkPopToolbar	: "ツールバー",
DlgLnkPopFullScrn	: "全画面モード(IE)",
DlgLnkPopDependent	: "开いたウィンドウに连动して闭じる (Netscape)",
DlgLnkPopWidth		: "幅",
DlgLnkPopHeight		: "高さ",
DlgLnkPopLeft		: "左端からの座标で指定",
DlgLnkPopTop		: "上端からの座标で指定",

DlnLnkMsgNoUrl		: "リンクURLを入力してください。",
DlnLnkMsgNoEMail	: "メールアドレスを入力してください。",
DlnLnkMsgNoAnchor	: "アンカーを选択してください。",
DlnLnkMsgInvPopName	: "ポップ?アップ名は英字で始まる文字で指定してくだい。ポップ?アップ名にスペースは含めません",

// Color Dialog
DlgColorTitle		: "色选択",
DlgColorBtnClear	: "クリア",
DlgColorHighlight	: "ハイライト",
DlgColorSelected	: "选択色",

// Smiley Dialog
DlgSmileyTitle		: "顔文字挿入",

// Special Character Dialog
DlgSpecialCharTitle	: "特殊文字选択",

// Table Dialog
DlgTableTitle		: "テーブル プロパティ",
DlgTableRows		: "行",
DlgTableColumns		: "列",
DlgTableBorder		: "ボーダーサイズ",
DlgTableAlign		: "キャプションの整列",
DlgTableAlignNotSet	: "<なし>",
DlgTableAlignLeft	: "左",
DlgTableAlignCenter	: "中央",
DlgTableAlignRight	: "右",
DlgTableWidth		: "テーブル幅",
DlgTableWidthPx		: "ピクセル",
DlgTableWidthPc		: "パーセント",
DlgTableHeight		: "テーブル高さ",
DlgTableCellSpace	: "セル内余白",
DlgTableCellPad		: "セル内间隔",
DlgTableCaption		: "???????",
DlgTableSummary		: "テーブル目的/构造",

// Table Cell Dialog
DlgCellTitle		: "セル プロパティ",
DlgCellWidth		: "幅",
DlgCellWidthPx		: "ピクセル",
DlgCellWidthPc		: "パーセント",
DlgCellHeight		: "高さ",
DlgCellWordWrap		: "折り返し",
DlgCellWordWrapNotSet	: "<なし>",
DlgCellWordWrapYes	: "Yes",
DlgCellWordWrapNo	: "No",
DlgCellHorAlign		: "セル横の整列",
DlgCellHorAlignNotSet	: "<なし>",
DlgCellHorAlignLeft	: "左",
DlgCellHorAlignCenter	: "中央",
DlgCellHorAlignRight: "右",
DlgCellVerAlign		: "セル縦の整列",
DlgCellVerAlignNotSet	: "<なし>",
DlgCellVerAlignTop	: "上",
DlgCellVerAlignMiddle	: "中央",
DlgCellVerAlignBottom	: "下",
DlgCellVerAlignBaseline	: "ベースライン",
DlgCellRowSpan		: "縦幅(行数)",
DlgCellCollSpan		: "横幅(列数)",
DlgCellBackColor	: "背景色",
DlgCellBorderColor	: "ボーダーカラー",
DlgCellBtnSelect	: "选択...",

// Find and Replace Dialog
DlgFindAndReplaceTitle	: "検索して置换",

// Find Dialog
DlgFindTitle		: "検索",
DlgFindFindBtn		: "検索",
DlgFindNotFoundMsg	: "指定された文字列は见つかりませんでした。",

// Replace Dialog
DlgReplaceTitle			: "置き换え",
DlgReplaceFindLbl		: "検索する文字列:",
DlgReplaceReplaceLbl	: "置换えする文字列:",
DlgReplaceCaseChk		: "部分一致",
DlgReplaceReplaceBtn	: "置换え",
DlgReplaceReplAllBtn	: "すべて置换え",
DlgReplaceWordChk		: "単语単位で一致",

// Paste Operations / Dialog
PasteErrorCut	: "ブラウザーのセキュリティ设定によりエディタの切り取り操作が自动で実行することができません。実行するには手动でキーボードの(Ctrl+X)を使用してください。",
PasteErrorCopy	: "ブラウザーのセキュリティ设定によりエディタのコピー操作が自动で実行することができません。実行するには手动でキーボードの(Ctrl+C)を使用してください。",

PasteAsText		: "プレーンテキスト贴り付け",
PasteFromWord	: "ワード文章から贴り付け",

DlgPasteMsg2	: "キーボード(<STRONG>Ctrl+V</STRONG>)を使用して、次の入力エリア内で贴って、<STRONG>OK</STRONG>を押してください。",
DlgPasteSec		: "ブラウザのセキュリティ设定により、エディタはクリップボード?データに直接アクセスすることができません。このウィンドウは贴り付け操作を行う度に表示されます。",
DlgPasteIgnoreFont		: "FontタグのFace属性を无视します。",
DlgPasteRemoveStyles	: "スタイル定义を削除します。",

// Color Picker
ColorAutomatic	: "自动",
ColorMoreColors	: "その他の色...",

// Document Properties
DocProps		: "文书 プロパティ",

// Anchor Dialog
DlgAnchorTitle		: "アンカー プロパティ",
DlgAnchorName		: "アンカー名",
DlgAnchorErrorName	: "アンカー名を必ず入力してください。",

// Speller Pages Dialog
DlgSpellNotInDic		: "辞书にありません",
DlgSpellChangeTo		: "変更",
DlgSpellBtnIgnore		: "无视",
DlgSpellBtnIgnoreAll	: "すべて无视",
DlgSpellBtnReplace		: "置换",
DlgSpellBtnReplaceAll	: "すべて置换",
DlgSpellBtnUndo			: "やり直し",
DlgSpellNoSuggestions	: "- 该当なし -",
DlgSpellProgress		: "スペルチェック処理中...",
DlgSpellNoMispell		: "スペルチェック完了: スペルの误りはありませんでした",
DlgSpellNoChanges		: "スペルチェック完了: 语句は変更されませんでした",
DlgSpellOneChange		: "スペルチェック完了: １语句変更されました",
DlgSpellManyChanges		: "スペルチェック完了: %1 语句変更されました",

IeSpellDownload			: "スペルチェッカーがインストールされていません。今すぐダウンロードしますか?",

// Button Dialog
DlgButtonText		: "テキスト (値)",
DlgButtonType		: "タイプ",
DlgButtonTypeBtn	: "ボタン",
DlgButtonTypeSbm	: "送信",
DlgButtonTypeRst	: "リセット",

// Checkbox and Radio Button Dialogs
DlgCheckboxName		: "名前",
DlgCheckboxValue	: "値",
DlgCheckboxSelected	: "选択済み",

// Form Dialog
DlgFormName		: "フォーム名",
DlgFormAction	: "アクション",
DlgFormMethod	: "メソッド",

// Select Field Dialog
DlgSelectName		: "名前",
DlgSelectValue		: "値",
DlgSelectSize		: "サイズ",
DlgSelectLines		: "行",
DlgSelectChkMulti	: "复数项目选択を许可",
DlgSelectOpAvail	: "利用可能なオプション",
DlgSelectOpText		: "选択项目名",
DlgSelectOpValue	: "选択项目値",
DlgSelectBtnAdd		: "追加",
DlgSelectBtnModify	: "编集",
DlgSelectBtnUp		: "上へ",
DlgSelectBtnDown	: "下へ",
DlgSelectBtnSetValue : "选択した値を设定",
DlgSelectBtnDelete	: "削除",

// Textarea Dialog
DlgTextareaName	: "名前",
DlgTextareaCols	: "列",
DlgTextareaRows	: "行",

// Text Field Dialog
DlgTextName			: "名前",
DlgTextValue		: "値",
DlgTextCharWidth	: "サイズ",
DlgTextMaxChars		: "最大长",
DlgTextType			: "タイプ",
DlgTextTypeText		: "テキスト",
DlgTextTypePass		: "パスワード入力",

// Hidden Field Dialog
DlgHiddenName	: "名前",
DlgHiddenValue	: "値",

// Bulleted List Dialog
BulletedListProp	: "个条书き プロパティ",
NumberedListProp	: "段落番号 プロパティ",
DlgLstStart			: "开始文字",
DlgLstType			: "タイプ",
DlgLstTypeCircle	: "白丸",
DlgLstTypeDisc		: "黒丸",
DlgLstTypeSquare	: "四角",
DlgLstTypeNumbers	: "アラビア数字 (1, 2, 3)",
DlgLstTypeLCase		: "英字小文字 (a, b, c)",
DlgLstTypeUCase		: "英字大文字 (A, B, C)",
DlgLstTypeSRoman	: "ローマ数字小文字 (i, ii, iii)",
DlgLstTypeLRoman	: "ローマ数字大文字 (I, II, III)",

// Document Properties Dialog
DlgDocGeneralTab	: "全般",
DlgDocBackTab		: "背景",
DlgDocColorsTab		: "色とマージン",
DlgDocMetaTab		: "メタデータ",

DlgDocPageTitle		: "ページタイトル",
DlgDocLangDir		: "言语文字表记の方向",
DlgDocLangDirLTR	: "左から右に表记(LTR)",
DlgDocLangDirRTL	: "右から左に表记(RTL)",
DlgDocLangCode		: "言语コード",
DlgDocCharSet		: "文字セット符号化",
DlgDocCharSetCE		: "Central European",
DlgDocCharSetCT		: "Chinese Traditional (Big5)",
DlgDocCharSetCR		: "Cyrillic",
DlgDocCharSetGR		: "Greek",
DlgDocCharSetJP		: "Japanese",
DlgDocCharSetKR		: "Korean",
DlgDocCharSetTR		: "Turkish",
DlgDocCharSetUN		: "Unicode (UTF-8)",
DlgDocCharSetWE		: "Western European",
DlgDocCharSetOther	: "他の文字セット符号化",

DlgDocDocType		: "文书タイプヘッダー",
DlgDocDocTypeOther	: "その他文书タイプヘッダー",
DlgDocIncXHTML		: "XHTML宣言をインクルード",
DlgDocBgColor		: "背景色",
DlgDocBgImage		: "背景画像 URL",
DlgDocBgNoScroll	: "スクロールしない背景",
DlgDocCText			: "テキスト",
DlgDocCLink			: "リンク",
DlgDocCVisited		: "アクセス済みリンク",
DlgDocCActive		: "アクセス中リンク",
DlgDocMargins		: "ページ?マージン",
DlgDocMaTop			: "上部",
DlgDocMaLeft		: "左",
DlgDocMaRight		: "右",
DlgDocMaBottom		: "下部",
DlgDocMeIndex		: "文书のキーワード(カンマ区切り)",
DlgDocMeDescr		: "文书の概要",
DlgDocMeAuthor		: "文书の作者",
DlgDocMeCopy		: "文书の着作権",
DlgDocPreview		: "プレビュー",

// Templates Dialog
Templates			: "テンプレート(雏形)",
DlgTemplatesTitle	: "テンプレート内容",
DlgTemplatesSelMsg	: "エディターで使用するテンプレートを选択してください。<br>(现在のエディタの内容は失われます):",
DlgTemplatesLoading	: "テンプレート一覧読み込み中. しばらくお待ちください...",
DlgTemplatesNoTpl	: "(テンプレートが定义されていません)",
DlgTemplatesReplace	: "现在のエディタの内容と置换えをします",

// About Dialog
DlgAboutAboutTab	: "バージョン情报",
DlgAboutBrowserInfoTab	: "ブラウザ情报",
DlgAboutLicenseTab	: "ライセンス",
DlgAboutVersion		: "バージョン",
DlgAboutInfo		: "より详しい情报はこちらで"
};

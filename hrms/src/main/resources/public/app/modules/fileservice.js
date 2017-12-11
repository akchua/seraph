define(['jquery', 'fileDownload'], function ($, fd) {
	return {
		downloadDocumentByFileName: function(fileName) {
			fd.fileDownload('/services/file/document/' + fileName)
			return false;
    	}
	};
});

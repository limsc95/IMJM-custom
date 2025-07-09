import axios from 'axios';

interface UploadResult {
    fileUrl: string;
    fileName: string;
    fileSize: number;
}

class AdminFileUploadService {
    private baseUrl = '/api/admin/chat';
    private readonly MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    // 단일 이미지 업로드
    async uploadImage(file: File, chatRoomId: number): Promise<UploadResult> {
        try {
            if (file.size > this.MAX_FILE_SIZE) {
                throw new Error(`파일 크기가 너무 큽니다. 최대 10MB까지 가능합니다.`);
            }

            const formData = new FormData();
            formData.append('file', file);
            formData.append('chatRoomId', chatRoomId.toString());

            const response = await axios.post(`${this.baseUrl}/upload`, formData, {
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            });

            return {
                fileUrl: response.data.fileUrl,
                fileName: file.name,
                fileSize: file.size
            };
        } catch (error) {
            console.error('이미지 업로드 실패:', error);
            throw error;
        }
    }

    // 다중 이미지 업로드
    async uploadMultipleImages(files: File[], chatRoomId: number): Promise<UploadResult[]> {
        try {
            const uploadPromises = files.map(file => this.uploadImage(file, chatRoomId));
            return await Promise.all(uploadPromises);
        } catch (error) {
            console.error('다중 이미지 업로드 실패:', error);
            throw error;
        }
    }
}

export default new AdminFileUploadService();
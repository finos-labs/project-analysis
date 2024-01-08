#Video playback doesn't automatically start for certain MP4s

Owner: google

Repo: ExoPlayer

Labels: 

## IanDBird (22 Jul 2014)

I've been testing a number of playback scenarios and have found that 'some' MP4s which are using the `DefaultRendererBuilder` don't actually start playback until a `seekTo` request is made.

Here's the log of the Demo application attempting to play one of these MP4s:

```
07-22 15:02:15.843  15430-15430/com.google.android.exoplayer.demo I/ViewRootImpl﹕ ViewRoot's Touch Event : Touch Down
07-22 15:02:15.923  15430-15430/com.google.android.exoplayer.demo I/ViewRootImpl﹕ ViewRoot's Touch Event : Touch UP
07-22 15:02:15.993  15430-15430/com.google.android.exoplayer.demo I/ActivityManager﹕ Timeline: Activity_launch_request id:com.google.android.exoplayer.demo time:71928056
07-22 15:02:16.093  15430-15430/com.google.android.exoplayer.demo I/ExoPlayerImpl﹕ Init 1.0.11
07-22 15:02:16.223  15430-15430/com.google.android.exoplayer.demo I/ActivityManager﹕ Timeline: Activity_idle id: android.os.BinderProxy@4293a680 time:71928282
07-22 15:02:16.383  15430-15659/com.google.android.exoplayer.demo V/MediaExtractor﹕ MediaExtractor create
07-22 15:02:16.383  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ GetMediaType start
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ GetMainType start
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ GetSubType start
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ GetFTYPSubType start
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ GetFTYPSubType end
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ GetSubType end
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ Pre GetMediaType [nMain:196608][nSub:196610]
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ GetMediaType [nMain:3][nSub:2]
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/LgeMediaDetector﹕ GetMediaType end
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo V/MediaExtractor﹕ Autodetected media content as 'video/mp4' with confidence 0.60
07-22 15:02:16.463  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ LG-CodeParser Version: LG_CodecParser_R1.0.0.11
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ SupportCodecEx : 104
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ #### Video mime video/avc
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/MediaCodecList﹕ getNewMediaCodecItem [0][DTSDecode][audio/dts]
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/MediaCodecList﹕ getNewMediaCodecItem [0][DDPDecode][audio/eac3]
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/MediaCodecList﹕ getNewMediaCodecItem [0][DDPDecode][audio/ac3]
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/MediaCodecList﹕ getNewMediaCodecItem [0][OMX.VisualOn.Video.Decoder.XXX][video/hevc]
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ #### componentName = OMX.qcom.video.decoder.avc
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchXmlParserImpl﹕ [BrunchXmlParserImpl::findIndexByKey] typeIndex < 0
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchUsedCodecEx﹕ [removeUsedCodecExLastItem] size = 8
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchXmlParserImpl﹕ [BrunchXmlParserImpl::findIndexByKey] typeIndex < 0
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchUsedCodecEx﹕ [removeUsedCodecExLastItem] size = 7
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchXmlParserImpl﹕ [BrunchXmlParserImpl::findIndexByKey] typeIndex < 0
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchUsedCodecEx﹕ [removeUsedCodecExLastItem] size = 6
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchXmlParserImpl﹕ [BrunchXmlParserImpl::findIndexByKey] typeIndex < 0
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchUsedCodecEx﹕ [removeUsedCodecExLastItem] size = 5
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchXmlParserImpl﹕ [BrunchXmlParserImpl::findIndexByKey] typeIndex < 0
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchUsedCodecEx﹕ [removeUsedCodecExLastItem] size = 4
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchXmlParserImpl﹕ [BrunchXmlParserImpl::findIndexByKey] typeIndex < 0
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchUsedCodecEx﹕ [removeUsedCodecExLastItem] size = 3
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchXmlParserImpl﹕ [BrunchXmlParserImpl::findIndexByKey] typeIndex < 0
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchUsedCodecEx﹕ [removeUsedCodecExLastItem] size = 2
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchXmlParserImpl﹕ [BrunchXmlParserImpl::findIndexByKey] typeIndex < 0
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo I/BrunchUsedCodecEx﹕ [removeUsedCodecExLastItem] size = 1
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo W/BrunchUsedCodecImpl﹕ [isUsedCodec] Used codec!!!!! = video/avc
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ #### componentName = OMX.google.h264.decoder
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo W/BrunchUsedCodecImpl﹕ [isUsedCodec] Used codec!!!!! = video/avc
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ SupportCodecEx : 201
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ #### Audio mime audio/mp4a-latm
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ #### componentName = OMX.google.aac.decoder
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo W/BrunchUsedCodecImpl﹕ [isUsedCodec] Used codec!!!!! = audio/mp4a-latm
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ #### componentName = AACDecoder
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo W/BrunchUsedCodecImpl﹕ [isUsedCodec] Used codec!!!!! = audio/mp4a-latm
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo E/MediaExtractorHelper﹕ #### componentName = OMX.qcom.audio.decoder.multiaac
07-22 15:02:16.473  15430-15659/com.google.android.exoplayer.demo W/BrunchUsedCodecImpl﹕ [isUsedCodec] Used codec!!!!! = audio/mp4a-latm
07-22 15:02:16.513  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.513  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.513  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.513  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.513  15430-15659/com.google.android.exoplayer.demo I/OMXClient﹕ Using client-side OMX mux.
07-22 15:02:16.523  15430-15679/com.google.android.exoplayer.demo I/OMXClient﹕ Using client-side OMX mux.
07-22 15:02:16.533  15430-15679/com.google.android.exoplayer.demo E/ACodec﹕ [OMX.qcom.video.decoder.avc] storeMetaDataInBuffers failed w/ err -2147483648
07-22 15:02:16.533  15430-15679/com.google.android.exoplayer.demo I/ACodec﹕ DRC Mode: Port Reconfig Mode
07-22 15:02:16.533  15430-15679/com.google.android.exoplayer.demo I/ExtendedCodec﹕ Enabling timestamp reordering
07-22 15:02:16.583  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.583  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.583  15430-15659/com.google.android.exoplayer.demo I/OMXClient﹕ Using client-side OMX mux.
07-22 15:02:16.583  15430-15659/com.google.android.exoplayer.demo E/OMXMaster﹕ A component of name 'OMX.qcom.audio.decoder.aac' already exists, ignoring this one.
07-22 15:02:16.583  15430-15659/com.google.android.exoplayer.demo I/﹕ @@@VOLOG Info THD 625AE718:    VOOMXPlugin.cpp  VOOMXPlugin  59    open libvoOMXOne.so successfully. 0X62D4024C
07-22 15:02:16.583  15430-15659/com.google.android.exoplayer.demo E/﹕ VOLOG Info THD 625AE718 voCOMXBaseConfig.cpp Open 368  The config file vomeOne.cfg could not be opened!
07-22 15:02:16.603  15430-15685/com.google.android.exoplayer.demo I/OMXClient﹕ Using client-side OMX mux.
07-22 15:02:16.603  15430-15685/com.google.android.exoplayer.demo E/OMXMaster﹕ A component of name 'OMX.qcom.audio.decoder.aac' already exists, ignoring this one.
07-22 15:02:16.603  15430-15685/com.google.android.exoplayer.demo I/﹕ @@@VOLOG Info THD 625ACF08:    VOOMXPlugin.cpp  VOOMXPlugin  59    open libvoOMXOne.so successfully. 0X62D40004
07-22 15:02:16.603  15430-15685/com.google.android.exoplayer.demo E/﹕ VOLOG Info THD 625ACF08 voCOMXBaseConfig.cpp Open 368  The config file vomeOne.cfg could not be opened!
07-22 15:02:16.613  15430-15686/com.google.android.exoplayer.demo I/SoftAAC2﹕ Reconfiguring decoder: 0->48000 Hz, 0->2 channels
07-22 15:02:16.623  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.623  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.623  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getOutputSamplingRate() no output descriptor for output 2 in gOutputs
07-22 15:02:16.623  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getSamplingRate() streamType 3, output 2, sampling rate 48000
07-22 15:02:16.623  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getFrameCount() streamType 3, output 2, frameCount 960
07-22 15:02:16.623  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getLatency() streamType 3, output 2, latency 160
07-22 15:02:16.623  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ getMinFrameCount=7680: afFrameCount=960, minBufCount=8, afSampleRate=48000, afLatency=160
07-22 15:02:16.633  15430-15442/com.google.android.exoplayer.demo V/AudioSystem﹕ ioConfigChanged() event 0, ioHandle 3
07-22 15:02:16.633  15430-15442/com.google.android.exoplayer.demo V/AudioSystem﹕ ioConfigChanged() new output samplingRate 48000, format 1 channel mask 0x3 frameCount 960 latency 84
07-22 15:02:16.633  15430-15442/com.google.android.exoplayer.demo V/AudioSystem﹕ ioConfigChanged() event 0, ioHandle 2
07-22 15:02:16.633  15430-15442/com.google.android.exoplayer.demo V/AudioSystem﹕ ioConfigChanged() new output samplingRate 48000, format 1 channel mask 0x3 frameCount 960 latency 160
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getFrameCount() streamType 3, output 2, frameCount 960
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getOutputSamplingRate() reading from output desc
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getSamplingRate() streamType 3, output 2, sampling rate 48000
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ sampleRate 48000, channelMask 0x3, format 1
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ streamType 3
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ set() streamType 3, sampleRate 48000, format 1, frameCount 30720, flags 0000
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getLatency() streamType 3, output 2, latency 160
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getFrameCount() streamType 3, output 2, frameCount 960
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getOutputSamplingRate() reading from output desc
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getSamplingRate() streamType 3, output 2, sampling rate 48000
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ createTrack_l() output 2 afLatency 160
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ afFrameCount=960, minBufCount=8, afSampleRate=48000, afLatency=160
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ minFrameCount: 7680, afFrameCount=960, minBufCount=8, sampleRate=48000, afSampleRate=48000, afLatency=160
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ lge.normalizer.param = version2.0/true/14.5/true/8230/1.0/2050/0.55
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:version = version2.0
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:Enable = "true"
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: SoundNormalizer:Enable set to true
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerMakeupGain = 14.5
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:PreFilter_HPF_350Hz = "true"
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: AudioEffect:SoundNormalizer:PreFilter_HPF_350Hz set to true
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerLimiterThreshold = 8230
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerLimiterSlope = 1.00
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerCompressorThreshold = 2050
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerCompressorSlope = 0.55
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ NormalizerV2(session: 403): mNormalizerV2->process(framecount: 1)
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ NormalizerV2: channelCount = 2, mStreamType = 3, mFlags = 0
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.633  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.643  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.643  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.653  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.653  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.663  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.663  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.673  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.673  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.683  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.683  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.693  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.693  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.703  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.703  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.713  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.713  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.723  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.723  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.733  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.733  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.743  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.743  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:16.753  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:16.753  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:18.573  15430-15674/com.google.android.exoplayer.demo I/NuCachedSource2﹕ Cache full, done prefetching for now
```

The application then sits there, thinking it's playing, but nothing happening. If you attempt to pause / play, nothing happens. Only when you seek will playback actually start. Here's the log immediately after my first seek:

```
07-22 15:02:26.853  15430-15430/com.google.android.exoplayer.demo I/ViewRootImpl﹕ ViewRoot's Touch Event : Touch Down
07-22 15:02:26.903  15430-15430/com.google.android.exoplayer.demo I/ViewRootImpl﹕ ViewRoot's Touch Event : Touch UP
07-22 15:02:27.813  15430-15430/com.google.android.exoplayer.demo I/ViewRootImpl﹕ ViewRoot's Touch Event : Touch Down
07-22 15:02:27.833  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.833  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.863  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.863  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.863  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.873  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.883  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.883  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.903  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.903  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.903  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.903  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.903  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.903  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.913  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.913  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.913  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.913  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.913  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.913  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.923  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.923  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.933  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.933  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.933  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.933  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.943  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.943  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.943  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.943  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.953  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.953  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.963  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.963  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.963  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.963  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.973  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.973  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getFrameCount() streamType 3, output 2, frameCount 960
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getOutputSamplingRate() reading from output desc
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getSamplingRate() streamType 3, output 2, sampling rate 48000
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ sampleRate 48000, channelMask 0x3, format 1
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ streamType 3
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ set() streamType 3, sampleRate 48000, format 1, frameCount 30720, flags 0000
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getLatency() streamType 3, output 2, latency 160
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getFrameCount() streamType 3, output 2, frameCount 960
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getOutputSamplingRate() reading from output desc
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getSamplingRate() streamType 3, output 2, sampling rate 48000
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ createTrack_l() output 2 afLatency 160
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ afFrameCount=960, minBufCount=8, afSampleRate=48000, afLatency=160
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ minFrameCount: 7680, afFrameCount=960, minBufCount=8, sampleRate=48000, afSampleRate=48000, afLatency=160
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ lge.normalizer.param = version2.0/true/14.5/true/8230/1.0/2050/0.55
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:version = version2.0
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:Enable = "true"
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: SoundNormalizer:Enable set to true
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerMakeupGain = 14.5
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:PreFilter_HPF_350Hz = "true"
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: AudioEffect:SoundNormalizer:PreFilter_HPF_350Hz set to true
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerLimiterThreshold = 8230
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerLimiterSlope = 1.00
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerCompressorThreshold = 2050
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerCompressorSlope = 0.55
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ NormalizerV2(session: 403): mNormalizerV2->process(framecount: 1)
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ NormalizerV2: channelCount = 2, mStreamType = 3, mFlags = 0
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:27.983  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.003  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.003  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.003  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.003  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.013  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.013  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.023  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.023  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.033  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.033  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.053  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.053  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.053  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.053  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.063  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.063  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.073  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.073  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.083  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.083  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.093  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.093  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.103  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.103  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.103  15430-15430/com.google.android.exoplayer.demo I/ViewRootImpl﹕ ViewRoot's Touch Event : Touch UP
07-22 15:02:28.113  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.113  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.123  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.123  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.123  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.123  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getFrameCount() streamType 3, output 2, frameCount 960
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getOutputSamplingRate() reading from output desc
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getSamplingRate() streamType 3, output 2, sampling rate 48000
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ sampleRate 48000, channelMask 0x3, format 1
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ streamType 3
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ set() streamType 3, sampleRate 48000, format 1, frameCount 30720, flags 0000
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getLatency() streamType 3, output 2, latency 160
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getFrameCount() streamType 3, output 2, frameCount 960
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getOutputSamplingRate() reading from output desc
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioSystem﹕ getSamplingRate() streamType 3, output 2, sampling rate 48000
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ createTrack_l() output 2 afLatency 160
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ afFrameCount=960, minBufCount=8, afSampleRate=48000, afLatency=160
07-22 15:02:28.133  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ minFrameCount: 7680, afFrameCount=960, minBufCount=8, sampleRate=48000, afSampleRate=48000, afLatency=160
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ lge.normalizer.param = version2.0/true/14.5/true/8230/1.0/2050/0.55
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:version = version2.0
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:Enable = "true"
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: SoundNormalizer:Enable set to true
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerMakeupGain = 14.5
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ SoundNormalizer:PreFilter_HPF_350Hz = "true"
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: AudioEffect:SoundNormalizer:PreFilter_HPF_350Hz set to true
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerLimiterThreshold = 8230
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerLimiterSlope = 1.00
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerCompressorThreshold = 2050
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/SoundNormalizerUtil﹕ normalizer V2: normalizerCompressorSlope = 0.55
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ NormalizerV2(session: 403): mNormalizerV2->process(framecount: 1)
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo V/AudioTrack﹕ NormalizerV2: channelCount = 2, mStreamType = 3, mFlags = 0
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.143  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.153  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.153  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.163  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.163  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.173  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.173  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.183  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.183  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.193  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.193  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.203  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.203  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.213  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.213  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.223  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.223  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.233  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.233  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.243  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.243  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.253  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.253  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.263  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.263  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.273  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.273  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.283  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.283  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.563  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.563  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.583  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.583  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.613  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.613  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.663  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.663  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.703  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.703  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.753  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.753  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.783  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.783  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.833  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.833  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.893  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.953  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.953  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.973  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:28.973  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:28.983  15430-15430/com.google.android.exoplayer.demo I/ViewRootImpl﹕ ViewRoot's Touch Event : Touch Down
07-22 15:02:29.013  15430-15430/com.google.android.exoplayer.demo I/ViewRootImpl﹕ ViewRoot's Touch Event : Touch UP
07-22 15:02:30.033  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Re-check a condition - 'isMalformed = 0'
07-22 15:02:30.033  15430-15659/com.google.android.exoplayer.demo E/MPEG4Extractor﹕ [LGE-MP4P] Reset mWrongNALCounter.
07-22 15:02:33.613  15430-15674/com.google.android.exoplayer.demo I/NuCachedSource2﹕ Keep alive
07-22 15:02:48.703  15430-15674/com.google.android.exoplayer.demo I/NuCachedSource2﹕ Keep alive
```

In my own application, i'm working around the issue by detecting when the video is ready to be played and then immediately seeking to the expected offset, e.g.

``` java
  @Override
  public void onDrawnToSurface(Surface surface)
  {
    m_player.seekTo(m_offset);
  }
```

I would link an example of the problematic MP4, but I just need to check with someone first that it's okay to share. 


## chartelt (22 Jul 2014)

Educated guess: Edit Lists involved... ? Had the same issue with dash.js


## martinbonnin (22 Jul 2014)

had the same problem here with [this video](http://www.dailymotion.com/cdn/H264-1280x720/video/x20w6of.mp4?auth=1410854598-2560-z6kb2tel-c8430f2843d7230c9e6622ba1920c77d).
It does not happen all the time though. I just hooked a debugger to the app when this happens and  it looks like the we want to read a video sample from the FrameworkSampleSource but all it has is an audio sample. We do not read this audio sample because all audio buffers are full already...

edit: I do not have the exact same log. especially, the `[LGE-MP4P] Re-check a condition - 'isMalformed = 0` is not present in my logcat


## IanDBird (22 Jul 2014)

@chartelt - I've used mp4v2's mp4file tool to take a look at the problematic file but unless i'm missing something, doesn't appear to use Edit Lists (no edts):

```
Ians-MacBook-Pro-2:Desktop Ian$ mp4file --dump 265502.mp4 
"265502.mp4": Dumping meta-information...
 "265502.mp4": type ftyp (ftyp)
  "265502.mp4": majorBrand = isom
  "265502.mp4": minorVersion = 512 (0x00000200)
  "265502.mp4": <table entries suppressed>
 "265502.mp4": type moov (moov)
  "265502.mp4": type mvhd (moov.mvhd)
   "265502.mp4": version = 0 (0x00)
   "265502.mp4": flags = 0 (0x000000)
   "265502.mp4": creationTime = 0 (0x00000000)
   "265502.mp4": modificationTime = 0 (0x00000000)
   "265502.mp4": timeScale = 1000 (0x000003e8)
   "265502.mp4": duration = 146240 (0x00023b40)
   "265502.mp4": rate = 1.000000
   "265502.mp4": volume = 1.000000
   "265502.mp4": reserved1 = <70 bytes>
   "265502.mp4": nextTrackId = 65538 (0x00010002)
  "265502.mp4": type trak (moov.trak)
   "265502.mp4": type tkhd (moov.trak.tkhd)
    "265502.mp4": version = 0 (0x00)
    "265502.mp4": flags = 15 (0x00000f)
    "265502.mp4": creationTime = 0 (0x00000000)
    "265502.mp4": modificationTime = 3437342701 (0xcce1afed)
    "265502.mp4": trackId = 1 (0x00000001)
    "265502.mp4": reserved1 = <4 bytes>  00 00 00 00  |....|
    "265502.mp4": duration = 145918 (0x000239fe)
    "265502.mp4": reserved2 = <8 bytes>  00 00 00 00 00 00 00 00  |........|
    "265502.mp4": layer = 0 (0x0000)
    "265502.mp4": alternate_group = 0 (0x0000)
    "265502.mp4": volume = 0.000000
    "265502.mp4": reserved3 = <2 bytes>  00 00  |..|
    "265502.mp4": matrix = <36 bytes>
    "265502.mp4": width = 715.647034
    "265502.mp4": height = 308.000000
   "265502.mp4": type mdia (moov.trak.mdia)
    "265502.mp4": type mdhd (moov.trak.mdia.mdhd)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": creationTime = 0 (0x00000000)
     "265502.mp4": modificationTime = 0 (0x00000000)
     "265502.mp4": timeScale = 11993 (0x00002ed9)
     "265502.mp4": duration = 1750000 (0x001ab3f0)
     "265502.mp4": language = Undetermined (0x55c4)
     "265502.mp4": reserved = <2 bytes>  00 00  |..|
    "265502.mp4": type hdlr (moov.trak.mdia.hdlr)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": reserved1 = <4 bytes>  00 00 00 00  |....|
     "265502.mp4": handlerType = vide
     "265502.mp4": reserved2 = <12 bytes>  00 00 00 00 00 00 00 00 00 00 00 00  |............|
     "265502.mp4": name = VideoHandler
    "265502.mp4": type minf (moov.trak.mdia.minf)
     "265502.mp4": type vmhd (moov.trak.mdia.minf.vmhd)
      "265502.mp4": version = 0 (0x00)
      "265502.mp4": flags = 1 (0x000001)
      "265502.mp4": reserved = <8 bytes>  00 00 00 00 00 00 00 00  |........|
     "265502.mp4": type dinf (moov.trak.mdia.minf.dinf)
      "265502.mp4": type dref (moov.trak.mdia.minf.dinf.dref)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": type url  (moov.trak.mdia.minf.dinf.dref.url )
        "265502.mp4": version = 0 (0x00)
        "265502.mp4": flags = 1 (0x000001)
        "265502.mp4": location = (null)
     "265502.mp4": type stbl (moov.trak.mdia.minf.stbl)
      "265502.mp4": type stsd (moov.trak.mdia.minf.stbl.stsd)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": type avc1 (moov.trak.mdia.minf.stbl.stsd.avc1)
        "265502.mp4": reserved1 = <6 bytes>  00 00 00 00 00 00  |......|
        "265502.mp4": dataReferenceIndex = 1 (0x0001)
        "265502.mp4": reserved2 = <16 bytes>  00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00  |................|
        "265502.mp4": width = 720 (0x02d0)
        "265502.mp4": height = 308 (0x0134)
        "265502.mp4": reserved3 = <14 bytes>  00 48 00 00 00 48 00 00 00 00 00 00 00 01  |.H...H........|
        "265502.mp4": compressorName = 
        "265502.mp4": reserved4 = <4 bytes>  00 18 ff ff  |....|
        "265502.mp4": type pasp (moov.trak.mdia.minf.stbl.stsd.avc1.pasp)
         "265502.mp4": hSpacing = 6083 (0x000017c3)
         "265502.mp4": vSpacing = 6120 (0x000017e8)
        "265502.mp4": type avcC (moov.trak.mdia.minf.stbl.stsd.avc1.avcC)
         "265502.mp4": configurationVersion = 1 (0x01)
         "265502.mp4": AVCProfileIndication = 66 (0x42)
         "265502.mp4": profile_compatibility = 192 (0xc0)
         "265502.mp4": AVCLevelIndication = 30 (0x1e)
         "265502.mp4": reserved = 63 (0x3f) <6 bits>
         "265502.mp4": lengthSizeMinusOne = 3 (0x3) <2 bits>
         "265502.mp4": reserved1 = 7 (0x7) <3 bits>
         "265502.mp4": numOfSequenceParameterSets = 1 (0x01) <5 bits>
         "265502.mp4": <table entries suppressed>
         "265502.mp4": numOfPictureParameterSets = 1 (0x01)
         "265502.mp4": <table entries suppressed>
      "265502.mp4": type stts (moov.trak.mdia.minf.stbl.stts)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stss (moov.trak.mdia.minf.stbl.stss)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 301 (0x0000012d)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stsc (moov.trak.mdia.minf.stbl.stsc)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 3 (0x00000003)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stsz (moov.trak.mdia.minf.stbl.stsz)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": sampleSize = 0 (0x00000000)
       "265502.mp4": sampleCount = 3500 (0x00000dac)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stco (moov.trak.mdia.minf.stbl.stco)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 319 (0x0000013f)
       "265502.mp4": <table entries suppressed>
  "265502.mp4": type trak (moov.trak)
   "265502.mp4": type tkhd (moov.trak.tkhd)
    "265502.mp4": version = 0 (0x00)
    "265502.mp4": flags = 15 (0x00000f)
    "265502.mp4": creationTime = 0 (0x00000000)
    "265502.mp4": modificationTime = 3437342701 (0xcce1afed)
    "265502.mp4": trackId = 2 (0x00000002)
    "265502.mp4": reserved1 = <4 bytes>  00 00 00 00  |....|
    "265502.mp4": duration = 146226 (0x00023b32)
    "265502.mp4": reserved2 = <8 bytes>  00 00 00 00 00 00 00 00  |........|
    "265502.mp4": layer = 0 (0x0000)
    "265502.mp4": alternate_group = 1 (0x0001)
    "265502.mp4": volume = 1.000000
    "265502.mp4": reserved3 = <2 bytes>  00 00  |..|
    "265502.mp4": matrix = <36 bytes>
    "265502.mp4": width = 0.000000
    "265502.mp4": height = 0.000000
   "265502.mp4": type mdia (moov.trak.mdia)
    "265502.mp4": type mdhd (moov.trak.mdia.mdhd)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": creationTime = 0 (0x00000000)
     "265502.mp4": modificationTime = 0 (0x00000000)
     "265502.mp4": timeScale = 48000 (0x0000bb80)
     "265502.mp4": duration = 7018857 (0x006b1969)
     "265502.mp4": language = English (0x15c7)
     "265502.mp4": reserved = <2 bytes>  00 00  |..|
    "265502.mp4": type hdlr (moov.trak.mdia.hdlr)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": reserved1 = <4 bytes>  00 00 00 00  |....|
     "265502.mp4": handlerType = soun
     "265502.mp4": reserved2 = <12 bytes>  00 00 00 00 00 00 00 00 00 00 00 00  |............|
     "265502.mp4": name = SoundHandler
    "265502.mp4": type minf (moov.trak.mdia.minf)
     "265502.mp4": type smhd (moov.trak.mdia.minf.smhd)
      "265502.mp4": version = 0 (0x00)
      "265502.mp4": flags = 0 (0x000000)
      "265502.mp4": reserved = <4 bytes>  00 00 00 00  |....|
     "265502.mp4": type dinf (moov.trak.mdia.minf.dinf)
      "265502.mp4": type dref (moov.trak.mdia.minf.dinf.dref)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": type url  (moov.trak.mdia.minf.dinf.dref.url )
        "265502.mp4": version = 0 (0x00)
        "265502.mp4": flags = 1 (0x000001)
        "265502.mp4": location = (null)
     "265502.mp4": type stbl (moov.trak.mdia.minf.stbl)
      "265502.mp4": type stsd (moov.trak.mdia.minf.stbl.stsd)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": type mp4a (moov.trak.mdia.minf.stbl.stsd.mp4a)
        "265502.mp4": reserved1 = <6 bytes>  00 00 00 00 00 00  |......|
        "265502.mp4": dataReferenceIndex = 1 (0x0001)
        "265502.mp4": soundVersion = 0 (0x0000)
        "265502.mp4": reserved2 = <6 bytes>  00 00 00 00 00 00  |......|
        "265502.mp4": channels = 2 (0x0002)
        "265502.mp4": sampleSize = 16 (0x0010)
        "265502.mp4": compressionId = 0 (0x0000)
        "265502.mp4": packetSize = 0 (0x0000)
        "265502.mp4": timeScale = 3145728000 (0xbb800000)
        "265502.mp4": type esds (moov.trak.mdia.minf.stbl.stsd.mp4a.esds)
         "265502.mp4": version = 0 (0x00)
         "265502.mp4": flags = 0 (0x000000)
         "265502.mp4": ESID = 2 (0x0002)
         "265502.mp4": streamDependenceFlag = 0 (0x0) <1 bits>
         "265502.mp4": URLFlag = 0 (0x0) <1 bits>
         "265502.mp4": OCRstreamFlag = 0 (0x0) <1 bits>
         "265502.mp4": streamPriority = 0 (0x00) <5 bits>
         "265502.mp4": decConfigDescr
          "265502.mp4": objectTypeId = 64 (0x40)
          "265502.mp4": streamType = 5 (0x05) <6 bits>
          "265502.mp4": upStream = 0 (0x0) <1 bits>
          "265502.mp4": reserved = 1 (0x1) <1 bits>
          "265502.mp4": bufferSizeDB = 0 (0x000000) <24 bits>
          "265502.mp4": maxBitrate = 64006 (0x0000fa06)
          "265502.mp4": avgBitrate = 64006 (0x0000fa06)
          "265502.mp4": decSpecificInfo
           "265502.mp4": info = <2 bytes>  11 90  |..|
          "265502.mp4": profileLevelIndicationIndexDescr
         "265502.mp4": slConfigDescr
          "265502.mp4": predefined = 2 (0x02)
         "265502.mp4": ipiPtr
         "265502.mp4": ipIds
         "265502.mp4": ipmpDescrPtr
         "265502.mp4": langDescr
         "265502.mp4": qosDescr
         "265502.mp4": regDescr
         "265502.mp4": extDescr
      "265502.mp4": type stts (moov.trak.mdia.minf.stbl.stts)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 2 (0x00000002)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stsc (moov.trak.mdia.minf.stbl.stsc)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 319 (0x0000013f)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stsz (moov.trak.mdia.minf.stbl.stsz)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": sampleSize = 0 (0x00000000)
       "265502.mp4": sampleCount = 6855 (0x00001ac7)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stco (moov.trak.mdia.minf.stbl.stco)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 320 (0x00000140)
       "265502.mp4": <table entries suppressed>
  "265502.mp4": type trak (moov.trak)
   "265502.mp4": type tkhd (moov.trak.tkhd)
    "265502.mp4": version = 0 (0x00)
    "265502.mp4": flags = 1 (0x000001)
    "265502.mp4": creationTime = 3437342701 (0xcce1afed)
    "265502.mp4": modificationTime = 3437342701 (0xcce1afed)
    "265502.mp4": trackId = 65536 (0x00010000)
    "265502.mp4": reserved1 = <4 bytes>  00 00 00 00  |....|
    "265502.mp4": duration = 145918 (0x000239fe)
    "265502.mp4": reserved2 = <8 bytes>  00 00 00 00 00 00 00 00  |........|
    "265502.mp4": layer = 0 (0x0000)
    "265502.mp4": alternate_group = 0 (0x0000)
    "265502.mp4": volume = 0.000000
    "265502.mp4": reserved3 = <2 bytes>  00 00  |..|
    "265502.mp4": matrix = <36 bytes>
    "265502.mp4": width = 0.000000
    "265502.mp4": height = 0.000000
   "265502.mp4": type tref (moov.trak.tref)
    "265502.mp4": type hint (moov.trak.tref.hint)
     "265502.mp4": <table entries suppressed>
   "265502.mp4": type mdia (moov.trak.mdia)
    "265502.mp4": type mdhd (moov.trak.mdia.mdhd)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": creationTime = 3437342701 (0xcce1afed)
     "265502.mp4": modificationTime = 3437342701 (0xcce1afed)
     "265502.mp4": timeScale = 90000 (0x00015f90)
     "265502.mp4": duration = 13132660 (0x00c86374)
     "265502.mp4": language = Undetermined (0x55c4)
     "265502.mp4": reserved = <2 bytes>  00 00  |..|
    "265502.mp4": type hdlr (moov.trak.mdia.hdlr)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": reserved1 = <4 bytes>  00 00 00 00  |....|
     "265502.mp4": handlerType = hint
     "265502.mp4": reserved2 = <12 bytes>  00 00 00 00 00 00 00 00 00 00 00 00  |............|
     "265502.mp4": name = GPAC ISO Hint Handler
    "265502.mp4": type minf (moov.trak.mdia.minf)
     "265502.mp4": type hmhd (moov.trak.mdia.minf.hmhd)
      "265502.mp4": version = 0 (0x00)
      "265502.mp4": flags = 0 (0x000000)
      "265502.mp4": maxPduSize = 0 (0x0000)
      "265502.mp4": avgPduSize = 0 (0x0000)
      "265502.mp4": maxBitRate = 0 (0x00000000)
      "265502.mp4": avgBitRate = 0 (0x00000000)
      "265502.mp4": slidingAvgBitRate = 0 (0x00000000)
     "265502.mp4": type dinf (moov.trak.mdia.minf.dinf)
      "265502.mp4": type dref (moov.trak.mdia.minf.dinf.dref)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": type url  (moov.trak.mdia.minf.dinf.dref.url )
        "265502.mp4": version = 0 (0x00)
        "265502.mp4": flags = 1 (0x000001)
        "265502.mp4": location = (null)
     "265502.mp4": type stbl (moov.trak.mdia.minf.stbl)
      "265502.mp4": type stsd (moov.trak.mdia.minf.stbl.stsd)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": type rtp  (moov.trak.mdia.minf.stbl.stsd.rtp )
        "265502.mp4": reserved1 = <6 bytes>  00 00 00 00 00 00  |......|
        "265502.mp4": dataReferenceIndex = 1 (0x0001)
        "265502.mp4": hintTrackVersion = 1 (0x0001)
        "265502.mp4": highestCompatibleVersion = 1 (0x0001)
        "265502.mp4": maxPacketSize = 1450 (0x000005aa)
        "265502.mp4": type tims (moov.trak.mdia.minf.stbl.stsd.rtp .tims)
         "265502.mp4": timeScale = 90000 (0x00015f90)
      "265502.mp4": type stts (moov.trak.mdia.minf.stbl.stts)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1321 (0x00000529)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stss (moov.trak.mdia.minf.stbl.stss)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 301 (0x0000012d)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stsc (moov.trak.mdia.minf.stbl.stsc)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 3 (0x00000003)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stsz (moov.trak.mdia.minf.stbl.stsz)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": sampleSize = 0 (0x00000000)
       "265502.mp4": sampleCount = 3500 (0x00000dac)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stco (moov.trak.mdia.minf.stbl.stco)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 319 (0x0000013f)
       "265502.mp4": <table entries suppressed>
   "265502.mp4": type udta (moov.trak.udta)
    "265502.mp4": type hnti (moov.trak.udta.hnti)
     "265502.mp4": type sdp  (moov.trak.udta.hnti.sdp )
      "265502.mp4": sdpText = m=video 0 RTP/AVP 96
b=AS:1493
a=rtpmap:96 H264/90000
a=control:trackID=65536
a=fmtp:96 profile-level-id=42C01E; packetization-mode=1; sprop-parameter-sets=Z0LAHtkAtCn5//DiEONxAAADAfQAAF2yDxYuSA==,aMuPIA==
a=framesize:96 720-308

  "265502.mp4": type trak (moov.trak)
   "265502.mp4": type tkhd (moov.trak.tkhd)
    "265502.mp4": version = 0 (0x00)
    "265502.mp4": flags = 1 (0x000001)
    "265502.mp4": creationTime = 3437342701 (0xcce1afed)
    "265502.mp4": modificationTime = 3437342701 (0xcce1afed)
    "265502.mp4": trackId = 65537 (0x00010001)
    "265502.mp4": reserved1 = <4 bytes>  00 00 00 00  |....|
    "265502.mp4": duration = 146240 (0x00023b40)
    "265502.mp4": reserved2 = <8 bytes>  00 00 00 00 00 00 00 00  |........|
    "265502.mp4": layer = 0 (0x0000)
    "265502.mp4": alternate_group = 0 (0x0000)
    "265502.mp4": volume = 0.000000
    "265502.mp4": reserved3 = <2 bytes>  00 00  |..|
    "265502.mp4": matrix = <36 bytes>
    "265502.mp4": width = 0.000000
    "265502.mp4": height = 0.000000
   "265502.mp4": type tref (moov.trak.tref)
    "265502.mp4": type hint (moov.trak.tref.hint)
     "265502.mp4": <table entries suppressed>
   "265502.mp4": type mdia (moov.trak.mdia)
    "265502.mp4": type mdhd (moov.trak.mdia.mdhd)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": creationTime = 3437342701 (0xcce1afed)
     "265502.mp4": modificationTime = 3437342701 (0xcce1afed)
     "265502.mp4": timeScale = 48000 (0x0000bb80)
     "265502.mp4": duration = 7019520 (0x006b1c00)
     "265502.mp4": language = Undetermined (0x55c4)
     "265502.mp4": reserved = <2 bytes>  00 00  |..|
    "265502.mp4": type hdlr (moov.trak.mdia.hdlr)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": reserved1 = <4 bytes>  00 00 00 00  |....|
     "265502.mp4": handlerType = hint
     "265502.mp4": reserved2 = <12 bytes>  00 00 00 00 00 00 00 00 00 00 00 00  |............|
     "265502.mp4": name = GPAC ISO Hint Handler
    "265502.mp4": type minf (moov.trak.mdia.minf)
     "265502.mp4": type hmhd (moov.trak.mdia.minf.hmhd)
      "265502.mp4": version = 0 (0x00)
      "265502.mp4": flags = 0 (0x000000)
      "265502.mp4": maxPduSize = 0 (0x0000)
      "265502.mp4": avgPduSize = 0 (0x0000)
      "265502.mp4": maxBitRate = 0 (0x00000000)
      "265502.mp4": avgBitRate = 0 (0x00000000)
      "265502.mp4": slidingAvgBitRate = 0 (0x00000000)
     "265502.mp4": type dinf (moov.trak.mdia.minf.dinf)
      "265502.mp4": type dref (moov.trak.mdia.minf.dinf.dref)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": type url  (moov.trak.mdia.minf.dinf.dref.url )
        "265502.mp4": version = 0 (0x00)
        "265502.mp4": flags = 1 (0x000001)
        "265502.mp4": location = (null)
     "265502.mp4": type stbl (moov.trak.mdia.minf.stbl)
      "265502.mp4": type stsd (moov.trak.mdia.minf.stbl.stsd)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 1 (0x00000001)
       "265502.mp4": type rtp  (moov.trak.mdia.minf.stbl.stsd.rtp )
        "265502.mp4": reserved1 = <6 bytes>  00 00 00 00 00 00  |......|
        "265502.mp4": dataReferenceIndex = 1 (0x0001)
        "265502.mp4": hintTrackVersion = 1 (0x0001)
        "265502.mp4": highestCompatibleVersion = 1 (0x0001)
        "265502.mp4": maxPacketSize = 1449 (0x000005a9)
        "265502.mp4": type tims (moov.trak.mdia.minf.stbl.stsd.rtp .tims)
         "265502.mp4": timeScale = 48000 (0x0000bb80)
      "265502.mp4": type stts (moov.trak.mdia.minf.stbl.stts)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 274 (0x00000112)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stsc (moov.trak.mdia.minf.stbl.stsc)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 151 (0x00000097)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stsz (moov.trak.mdia.minf.stbl.stsz)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": sampleSize = 0 (0x00000000)
       "265502.mp4": sampleCount = 872 (0x00000368)
       "265502.mp4": <table entries suppressed>
      "265502.mp4": type stco (moov.trak.mdia.minf.stbl.stco)
       "265502.mp4": version = 0 (0x00)
       "265502.mp4": flags = 0 (0x000000)
       "265502.mp4": entryCount = 375 (0x00000177)
       "265502.mp4": <table entries suppressed>
   "265502.mp4": type udta (moov.trak.udta)
    "265502.mp4": type hnti (moov.trak.udta.hnti)
     "265502.mp4": type sdp  (moov.trak.udta.hnti.sdp )
      "265502.mp4": sdpText = m=audio 0 RTP/AVP 97
b=AS:64
a=rtpmap:97 mpeg4-generic/48000/2
a=control:trackID=65537
a=fmtp:97 profile-level-id=41; config=1190; streamType=5; mode=AAC-hbr; objectType=64; sizeLength=13; indexLength=3; indexDeltaLength=3

  "265502.mp4": type udta (moov.udta)
   "265502.mp4": type meta (moov.udta.meta)
    "265502.mp4": version = 0 (0x00)
    "265502.mp4": flags = 0 (0x000000)
    "265502.mp4": type hdlr (moov.udta.meta.hdlr)
     "265502.mp4": version = 0 (0x00)
     "265502.mp4": flags = 0 (0x000000)
     "265502.mp4": reserved1 = 0 (0x00000000)
     "265502.mp4": handlerType = <4 bytes>  6d 64 69 72  |mdir|
     "265502.mp4": reserved2 = <12 bytes>  61 70 70 6c 00 00 00 00 00 00 00 00  |appl........|
     "265502.mp4": name = <1 bytes>  00  |.|
    "265502.mp4": type ilst (moov.udta.meta.ilst)
     "265502.mp4": type ?too (moov.udta.meta.ilst.?too)
      "265502.mp4": type data (moov.udta.meta.ilst.?too.data)
       "265502.mp4": typeReserved = 0 (0x0000)
       "265502.mp4": typeSetIdentifier = 0 (0x00)
       "265502.mp4": typeCode = UTF-8 (0x01)
       "265502.mp4": locale = 0 (0x00000000)
       "265502.mp4": metadata = <13 bytes>  4c 61 76 66 35 34 2e 32 35 2e 31 30 34  |Lavf54.25.104|
   "265502.mp4": type hnti (moov.udta.hnti)
    "265502.mp4": type rtp  (moov.udta.hnti.rtp )
     "265502.mp4": descriptionFormat = sdp 
     "265502.mp4": sdpText = b=AS:1557
a=x-copyright: MP4/3GP File hinted with GPAC 0.4.5-DEV (build 16 - Feb  1 2008) - compiled by Kurtnoise (C)2000-2005 - http://gpac.sourceforge.net

 "265502.mp4": type mdat (mdat)
 "265502.mp4": type free (free)
 "265502.mp4": type free (free)
```


## chartelt (22 Jul 2014)

I used https://github.com/sannies/isoviewer and found edit list boxes for the file from martins link.
However both tracks are delayed by the same amount. Unfortunately I cannot spend more time right now.


## ojw28 (01 Aug 2014)

This is probably fixed on the dev branch. Please re-open / comment if not!


## IanDBird (01 Aug 2014)

Thanks so much, i'll give it a try!


## Drjacky (20 Oct 2015)

I can't play rtp, even on Dev branch!




import {ChannelCfg} from './channelcfg'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_channel_log",'tunnel')
export class ChannelLog extends BaseEntity{
	@Id()
	@Column({
		name:'channel_log_id',
		type:'int',
		nullable:false
	})
	channelLogId:number;

	@ManyToOne({entity:ChannelLog,lazyFetch:true})
	@JoinColumn({name:'channel_cfg_id',refName:'channel_cfg_id'})
	channelCfg:ChannelCfg;

	@Column({
		name:'value',
		type:'double',
		nullable:true
	})
	value:number;

}
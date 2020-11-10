
import {RoadLevel} from './roadlevel'
import {Area} from './area'
import {RoadFuncRel} from './roadfuncrel'
import {Tunnel} from './tunnel'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_road",'tunnel')
export class Road extends BaseEntity{
	@Id()
	@Column({
		name:'road_id',
		type:'int',
		nullable:false
	})
	roadId:number;

	@ManyToOne({entity:Road,lazyFetch:true})
	@JoinColumn({name:'road_level_id',refName:'road_level_id'})
	roadLevel:RoadLevel;

	@ManyToOne({entity:Road,lazyFetch:true})
	@JoinColumn({name:'area_id',refName:'area_id'})
	area:Area;

	@Column({
		name:'road_name',
		type:'string',
		nullable:true
	})
	roadName:string;

	@Column({
		name:'road_no',
		type:'string',
		nullable:true
	})
	roadNo:string;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	remarks:string;

	@OneToMany({entity:'RoadFuncRel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'road',lazyFetch:true})
	roadFuncRels:Array<RoadFuncRel>;

	@OneToMany({entity:'Tunnel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'road',lazyFetch:true})
	tunnels:Array<Tunnel>;

}